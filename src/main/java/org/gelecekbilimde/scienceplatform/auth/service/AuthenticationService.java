package org.gelecekbilimde.scienceplatform.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.UserVerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.response.TokenResponse;

public interface AuthenticationService {

	TokenResponse register(RegisterRequest request);

	TokenResponse login(LoginRequest request);

	TokenResponse generateGuestToken();

	Object refreshToken(HttpServletRequest request);

	void verify(UserVerifyRequest userVerifyRequest);

}
