package org.gelecekbilimde.scienceplatform.user.service;

import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserVerification;

public interface UserEmailService {

	void sendVerifyMessage(User user);

	void sendWelcomeMessage(User user);

	boolean userIsVerificated(UserVerification userVerification);

}
