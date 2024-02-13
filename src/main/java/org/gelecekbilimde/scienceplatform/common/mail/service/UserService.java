package org.gelecekbilimde.scienceplatform.common.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.common.mail.model.ConfirmationToken;
import org.gelecekbilimde.scienceplatform.common.mail.service.impl.EmailServiceImpl;
import org.gelecekbilimde.scienceplatform.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.user.enums.UserStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.repository.ConfirmationTokenRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final ConfirmationTokenRepository confirmationTokenRepository;
	private final EmailServiceImpl emailServiceImpl;

	public void confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		try {
			if (token != null) {
				User user = userRepository.findByEmail(token.getUser().getEmail()).orElseThrow(() -> new UserNotFoundException("User not found!"));
				user.setStatus(UserStatus.VERIFY);
				userRepository.save(user);
				Thread thread1 = new Thread(() -> emailServiceImpl.sendWelcomeMessage(user));
				thread1.start();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
