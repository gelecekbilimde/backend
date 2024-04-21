package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.dto.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.TokenRefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.UserVerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.response.TokenResponse;

public interface AuthenticationService {

	TokenResponse register(RegisterRequest request);

	TokenResponse login(LoginRequest request);

	TokenResponse generateGuestToken();

	TokenResponse refreshToken(TokenRefreshRequest refreshRequest);

	void verify(UserVerifyRequest userVerifyRequest);

}
