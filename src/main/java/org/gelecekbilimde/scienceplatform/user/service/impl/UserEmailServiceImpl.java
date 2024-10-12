package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.request.MailSendRequest;
import org.gelecekbilimde.scienceplatform.common.service.MailService;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
class UserEmailServiceImpl implements UserEmailService {

	@Value("${application.front-end.url}")
	public String frontEndUrl;

	private final MailService mailService;

	@Override
	public void sendVerifyMessage(String email, String verificationId) {
		Map<String, String> templateVariables = Map.of(
			"BASE_URL", frontEndUrl + "/auth/verify?verificationId=" + verificationId // todo frontendten url istenecek
		);
		MailSendRequest mailSendRequest = MailSendRequest.builder()
			.to(email)
			.templateFileName("user-verification.html")
			.templateVariables(templateVariables)
			.build();
		mailService.send(mailSendRequest);
	}

	@Override
	public void sendWelcomeMessage(String email) {
		MailSendRequest mailSendRequest = MailSendRequest.builder()
			.to(email)
			.templateFileName("user-welcome.html")
			.build();
		mailService.send(mailSendRequest);
	}

}
