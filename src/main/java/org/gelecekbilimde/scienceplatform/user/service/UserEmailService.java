package org.gelecekbilimde.scienceplatform.user.service;

public interface UserEmailService {

	void sendVerifyMessage(String email, String verificationId);

	void sendWelcomeMessage(String email);

}
