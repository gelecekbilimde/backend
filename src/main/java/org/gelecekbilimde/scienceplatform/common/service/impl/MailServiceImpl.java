package org.gelecekbilimde.scienceplatform.common.service.impl;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.common.model.enums.MailTemplate;
import org.gelecekbilimde.scienceplatform.common.model.request.MailSendRequest;
import org.gelecekbilimde.scienceplatform.common.service.MailService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;

	@Override
	public void send(MailSendRequest sendRequest) {

		CompletableFuture.runAsync(() -> this.sendEmail(sendRequest))
			.orTimeout(5, TimeUnit.SECONDS)
			.exceptionally(throwable -> {
				log.warn("Mail not sent to {} in 5 seconds with {} template", sendRequest.getTo(), sendRequest.getTemplate());
				throw new AsyncRequestTimeoutException();
			});

	}

	private void sendEmail(final MailSendRequest sendRequest) {
		try {

			MimeMessage mimeMessage = this.createMimeMessage(sendRequest);

			mailSender.send(mimeMessage);

			log.trace("Mail sent to {} with {} template", sendRequest.getTo(), sendRequest.getTemplate());

		} catch (Exception exception) {
			log.error("Received error while sending mail to {} with {} template", sendRequest.getTo(), sendRequest.getTemplate(), exception);
		}
	}

	private MimeMessage createMimeMessage(final MailSendRequest sendRequest) throws IOException, MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		String htmlContent = this.findTemplate(sendRequest.getTemplate());

		String title = this.findTitle(htmlContent);
		mimeMessage.setSubject(title);

		String htmlContentWithParameters = this.addParameters(htmlContent, sendRequest.getParameters());
		mimeMessage.setText(htmlContentWithParameters, "UTF-8", "html");

		mimeMessage.setFrom(new InternetAddress("smtp@gelecekbilimde.net", "Gelecek Bilimde"));

		for (String to : sendRequest.getTo()) {
			mimeMessage.addRecipients(Message.RecipientType.TO, to);
		}
		return mimeMessage;
	}

	private String findTemplate(MailTemplate template) throws IOException {
		ClassPathResource resource = new ClassPathResource("static/mail/template/" + template.getFile());
		byte[] binaryData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return new String(binaryData, StandardCharsets.UTF_8);
	}

	private String findTitle(String htmlContent) {
		return htmlContent.split("<title>")[1].split("</title>")[0];
	}

	private String addParameters(String htmlContent, Map<String, Object> parameters) {
		String template = htmlContent;
		for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
			template = template.replace("{" + parameter.getKey() + "}", parameter.getValue().toString());
		}
		return template;
	}

}
