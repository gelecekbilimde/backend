package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.mail.model.EmailSendRequest;
import org.gelecekbilimde.scienceplatform.common.mail.service.EmailService;
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
			.build();
		userVerificationRepository.save(userVerification);

		Map<String, String> templateVariables = Map.of(
			"BASE_URL", BASE_URL + "/confirm-account?token=" + userVerification.getId() // TODO : this URL should be UI URL for the user to click and verify the account
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

		EmailSendRequest emailSendRequest = EmailSendRequest.builder()
			.to(user.getEmail())
			.templateFileName("user-welcome.html")
			.build();
		emailService.send(emailSendRequest);
	}

}
