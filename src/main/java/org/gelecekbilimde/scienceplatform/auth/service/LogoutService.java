package org.gelecekbilimde.scienceplatform.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface LogoutService {
	void authLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
