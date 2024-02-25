package org.gelecekbilimde.scienceplatform.common.mail.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.common.mail.model.EmailSendRequest;
import org.gelecekbilimde.scienceplatform.common.mail.service.EmailService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {

	private final JavaMailSender javaMailSender;

	@Override
	public void send(EmailSendRequest sendRequest) {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

			mimeMessageHelper.setTo(sendRequest.getTo());
			String emailContent = this.generateEmailContent(
				sendRequest.getTemplateFileName(),
				sendRequest.getTemplateVariables()
			);
			mimeMessageHelper.setSubject(this.getSubject(emailContent));
			mimeMessageHelper.setText(emailContent, true);

			javaMailSender.send(mimeMessage);
		} catch (Exception exception) {
			log.error("Error while sending email: {}", sendRequest.getTo(), exception);
		}

	}

	private String generateEmailContent(String templateName, Map<String, String> templateVariables) throws IOException {
		ClassPathResource htmlTemplate = new ClassPathResource("static/mailtemplate/".concat(templateName));
		byte[] htmlBytes = FileCopyUtils.copyToByteArray(htmlTemplate.getInputStream());
		String emailContent = new String(htmlBytes);

		for (Map.Entry<String, String> entry : templateVariables.entrySet()) {
			emailContent = emailContent.replace("${".concat(entry.getKey()).concat("}"), entry.getValue());
		}
		return emailContent;
	}

	private String getSubject(String emailContent) {
		return emailContent.split("<title>")[1].split("</title>")[0];
	}

}
