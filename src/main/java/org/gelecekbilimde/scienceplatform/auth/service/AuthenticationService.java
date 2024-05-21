package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.TokenRefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.UserVerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.TokenResponse;

public interface AuthenticationService {

	TokenResponse register(RegisterRequest request);

	TokenResponse login(LoginRequest request);

	TokenResponse generateGuestToken();

	TokenResponse refreshToken(TokenRefreshRequest refreshRequest);

	void verify(UserVerifyRequest userVerifyRequest);

}
