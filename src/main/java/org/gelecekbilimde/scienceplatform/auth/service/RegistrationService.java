package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.VerifyRequest;

public interface RegistrationService {

	void register(RegisterRequest request);

	void verify(VerifyRequest verifyRequest);

}
