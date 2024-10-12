package org.gelecekbilimde.scienceplatform.user.service;

public interface UserEmailService {

	void sendVerification(String email, String verificationId);

	void sendWelcome(String email);

}
