package org.gelecekbilimde.scienceplatform.common.service;

import org.gelecekbilimde.scienceplatform.common.model.MailSendRequest;

public interface MailService {

	void send(MailSendRequest sendRequest);

}
