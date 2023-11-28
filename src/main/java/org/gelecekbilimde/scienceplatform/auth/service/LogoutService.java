package org.gelecekbilimde.scienceplatform.auth.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.gelecekbilimde.scienceplatform.common.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.config.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

	private final InvalidTokenService invalidTokenService;
	private final JwtService jwtService;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (null == authHeader || !authHeader.startsWith("Bearer ")) {
			return;
		}

		final String token = authHeader.substring(7);
		final String tokenId = (String) jwtService.extractClaim(token,TokenClaims.JWT_ID.getValue());
		invalidTokenService.saveInvalidToken(tokenId);

		SecurityContextHolder.clearContext();

	}
}
