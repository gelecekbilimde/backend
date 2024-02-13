package org.gelecekbilimde.scienceplatform.common.mail.service;

import org.gelecekbilimde.scienceplatform.user.model.User;

public interface EmailService {
	void sendVerifyMessage(User user);

	void sendWelcomeMessage(User user);
}
