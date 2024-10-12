package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.enums.MailTemplate;
import org.gelecekbilimde.scienceplatform.common.model.request.MailSendRequest;
import org.gelecekbilimde.scienceplatform.common.service.MailService;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class UserEmailServiceImpl implements UserEmailService {

	@Value("${application.front-end.url}")
	private String frontEndUrl;

	private final MailService mailService;

	@Override
	public void sendVerification(final String email,
								 final String verificationId) {

		final Map<String, Object> parameters = Map.of(
			"BASE_URL", frontEndUrl + "/auth/verify?verificationId=" + verificationId
		);

		final MailSendRequest mailSendRequest = MailSendRequest.builder()
			.to(List.of(email))
			.template(MailTemplate.USER_VERIFICATION)
			.parameters(parameters)
			.build();

		mailService.send(mailSendRequest);
	}

	@Override
	public void sendWelcome(final String email) {

		final MailSendRequest mailSendRequest = MailSendRequest.builder()
			.to(List.of(email))
			.template(MailTemplate.USER_WELCOME)
			.build();

		mailService.send(mailSendRequest);
	}

}
