package org.gelecekbilimde.scienceplatform.common.mail.service;

import org.gelecekbilimde.scienceplatform.common.mail.model.EmailSendRequest;

public interface EmailService {

	void send(EmailSendRequest sendRequest);

}
