package org.gelecekbilimde.scienceplatform.auth.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.auth.service.LogoutService;
import org.gelecekbilimde.scienceplatform.common.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.config.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutHandler, LogoutService {

	private final InvalidTokenService invalidTokenService;
	private final JwtService jwtService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		authLogout(request, response, authentication);
	}

	@Override
	public void authLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		baseLogout(request);
	}

	private void baseLogout(HttpServletRequest request) {

		// TODO Burada Authentication içerisinde token bilgisi alabilrmiyiz
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (null == authHeader || !authHeader.startsWith("Bearer ")) {
			return;
		}

		final String token = authHeader.substring(7);
		final String tokenId = (String) jwtService.extractClaim(token, TokenClaims.JWT_ID.getValue());
		invalidTokenService.saveInvalidToken(tokenId);

		SecurityContextHolder.clearContext();
	}

}
