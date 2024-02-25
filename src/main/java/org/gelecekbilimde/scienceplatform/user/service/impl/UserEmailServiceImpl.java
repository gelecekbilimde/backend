package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.mail.model.EmailSendRequest;
import org.gelecekbilimde.scienceplatform.common.mail.service.EmailService;
import org.gelecekbilimde.scienceplatform.exception.UserVerifyException;
import org.gelecekbilimde.scienceplatform.user.enums.UserVerificationStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserVerification;
import org.gelecekbilimde.scienceplatform.user.repository.UserVerificationRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
class UserEmailServiceImpl implements UserEmailService {

	@Value("${application.base-url}")
	public String BASE_URL;

	private final EmailService emailService;

	private final UserVerificationRepository userVerificationRepository;

	@Override
	public void sendVerifyMessage(User user) {

		UserVerification userVerification = UserVerification.builder()
			.userId(user.getId())
			.status(UserVerificationStatus.WAIT)
			.build();
		userVerificationRepository.save(userVerification);

		Map<String, String> templateVariables = Map.of(
			"BASE_URL", BASE_URL + "/auth/verify?verificationId=" + userVerification.getId() // todo frontendten url istenecek
		);
		EmailSendRequest emailSendRequest = EmailSendRequest.builder()
			.to(user.getEmail())
			.templateFileName("user-verification.html")
			.templateVariables(templateVariables)
			.build();
		emailService.send(emailSendRequest);
	}

	@Override
	public void sendWelcomeMessage(User user) {
		UserVerification userVerification = userVerificationRepository.findByUserId(user.getId());
		if (userIsVerificated(userVerification)) {
			throw new UserVerifyException("User is already registered.");
		}
		userVerification.setStatus(UserVerificationStatus.COMPLETED);
		userVerificationRepository.save(userVerification);
		EmailSendRequest emailSendRequest = EmailSendRequest.builder()
			.to(user.getEmail())
			.templateFileName("user-welcome.html")
			.build();
		emailService.send(emailSendRequest);
	}

	@Override
	public boolean userIsVerificated(UserVerification userVerification) {
		return userVerification.getStatus() == UserVerificationStatus.COMPLETED;
	}

}
