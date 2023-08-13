package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.common.mail.EmailService;
import org.gelecekbilimde.scienceplatform.model.ConfirmationToken;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.ConfirmationTokenRepository;
import org.gelecekbilimde.scienceplatform.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{
	private final UserRepository userRepository;
	private final ConfirmationTokenRepository confirmationTokenRepository;
	private final EmailService emailService;

	public void confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			User user = userRepository.findByEmail(token.getUser().getEmail()).orElseThrow(()-> new UserNotFoundException("User not found!"));
			user.setEmailVerify(true);
			userRepository.save(user);
			emailService.sendWelcomeMessage(user);
		}
	}
}
