package org.gelecekbilimde.scienceplatform.user.service;

import org.gelecekbilimde.scienceplatform.user.model.User;

public interface UserEmailService {

	void sendVerifyMessage(User user);

	void sendWelcomeMessage(User user);

}
