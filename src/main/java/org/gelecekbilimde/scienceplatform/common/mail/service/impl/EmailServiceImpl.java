package org.gelecekbilimde.scienceplatform.common.mail.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.common.mail.model.ConfirmationToken;
import org.gelecekbilimde.scienceplatform.common.mail.service.EmailService;
import org.gelecekbilimde.scienceplatform.exception.MailSendingException;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	@Value("${application.base-url}")
	public String BASE_URL;

	private final JavaMailSender javaMailSender;

	private final ConfirmationTokenRepository confirmationTokenRepository;

	@Override
	public void sendVerifyMessage(User user) {

		ConfirmationToken confirmationToken = new ConfirmationToken();
		confirmationToken.setUser(user);
		confirmationToken.setTokenId(Util.generateUUID());
		confirmationTokenRepository.save(confirmationToken);

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Hesabınızı Onaylayın!");
			ClassPathResource htmlTemplate = new ClassPathResource("static/mailtemplate/mailtemplate.html");
			byte[] htmlBytes = FileCopyUtils.copyToByteArray(htmlTemplate.getInputStream());
			String emailContent = new String(htmlBytes);

			String confirmationLink = BASE_URL + "/confirm-account?token=" + confirmationToken.getConfirmationToken();
			emailContent = emailContent.replace("${BASE_URL}", confirmationLink);
			helper.setText(emailContent, true);


			Thread thread = new Thread(() -> javaMailSender.send(mimeMessage));
			thread.start();
		} catch (Exception e) {
			throw new MailSendingException("E-posta gönderme hatası oluştu", e);
		}
	}


	@Override
	public void sendWelcomeMessage(User user) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		try {
			helper.setTo(user.getEmail());
			helper.setSubject("Gelecek Bilimde Ekibine Hoşgeldiniz!");
			ClassPathResource htmlTemplate = new ClassPathResource("static/mailtemplate/mailtemplate-verify.html");
			byte[] htmlBytes = FileCopyUtils.copyToByteArray(htmlTemplate.getInputStream());
			String emailContent = new String(htmlBytes);
			helper.setText(emailContent, true);

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new MailSendingException("E-posta gönderme hatası oluştu", e);
		}
	}
}
