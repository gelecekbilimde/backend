package org.gelecekbilimde.scienceplatform.common.mail;

import org.gelecekbilimde.scienceplatform.model.ConfirmationToken;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${application.base-url}")
	public String BASE_URL;

	private final JavaMailSender javaMailSender;

	private final ConfirmationTokenRepository confirmationTokenRepository;

	public EmailService(JavaMailSender javaMailSender, ConfirmationTokenRepository confirmationTokenRepository) {
		this.javaMailSender = javaMailSender;
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}

	public void sendVerifyMessage(User user){
		ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenRepository.save(confirmationToken);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setText("To confirm your account, please click here : " + BASE_URL
			+"/mail/confirm-account?token="+confirmationToken.getConfirmationToken());
		sendEmail(mailMessage);
	}

	public void sendWelcomeMessage(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Gelecek Bilimde Ekibine Hoşgeldiniz!");
		mailMessage.setText("Ekibimize hoşgeldiniz. Siz değerli kullanıcıları aramızda görmek güzel. ");
		sendEmail(mailMessage);
	}
}
