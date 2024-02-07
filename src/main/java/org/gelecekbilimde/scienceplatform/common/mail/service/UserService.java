package org.gelecekbilimde.scienceplatform.common.mail.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.mail.model.ConfirmationToken;
import org.gelecekbilimde.scienceplatform.common.mail.payload.ConfirmAccountResponse;
import org.gelecekbilimde.scienceplatform.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.user.enums.UserStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.repository.ConfirmationTokenRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
	private final UserRepository userRepository;
	private final ConfirmationTokenRepository confirmationTokenRepository;
	private final EmailService emailService;


	// TODO: asenkron olacak
	public ConfirmAccountResponse confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		try {
			if (token != null) {
				User user = userRepository.findByEmail(token.getUser().getEmail()).orElseThrow(() -> new UserNotFoundException("User not found!"));
				user.setStatus(UserStatus.VERIFY);
				userRepository.save(user);
				Thread thread1 = new Thread(() -> emailService.sendWelcomeMessage(user));
				thread1.start();
				return ConfirmAccountResponse.builder().httpStatus(HttpStatus.OK).message("User has been confirmed").build();
			}
		} catch (Exception e) {
			return ConfirmAccountResponse.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).message("Internal Server Error").build();
		}
		return ConfirmAccountResponse.builder().httpStatus(HttpStatus.NOT_ACCEPTABLE).message("Invalid token").build();
	}
}
