package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.mail.model.EmailSendRequest;
import org.gelecekbilimde.scienceplatform.common.mail.service.EmailService;
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

	@Override
	public void sendVerifyMessage(String email, String verificationId) {
		Map<String, String> templateVariables = Map.of(
			"BASE_URL", BASE_URL + "/auth/verify?verificationId=" + verificationId // todo frontendten url istenecek
		);
		EmailSendRequest emailSendRequest = EmailSendRequest.builder()
			.to(email)
			.templateFileName("user-verification.html")
			.templateVariables(templateVariables)
			.build();
		emailService.send(emailSendRequest);
	}

	@Override
	public void sendWelcomeMessage(String email) {
		EmailSendRequest emailSendRequest = EmailSendRequest.builder()
			.to(email)
			.templateFileName("user-welcome.html")
			.build();
		emailService.send(emailSendRequest);
	}

}
