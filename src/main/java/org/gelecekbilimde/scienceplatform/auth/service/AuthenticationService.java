package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RefreshRequest;

public interface AuthenticationService {

	Token login(LoginRequest request);

	Token refresh(RefreshRequest refreshRequest);

	void logout(String refreshToken);

}
