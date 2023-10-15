package org.gelecekbilimde.scienceplatform.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.repository.TokenRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {


	private final TokenRepository tokenRepository;
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String jwt;

		if (null == authHeader || !authHeader.startsWith("Bearer ")) {
			return;
		}
		jwt = authHeader.substring(7);

		var storedToken = tokenRepository.findByJwt(jwt)
			.orElse(null);

		if (storedToken != null){
			storedToken.setExpired(true);
			storedToken.setRevoked(true);
			tokenRepository.save(storedToken);
			SecurityContextHolder.clearContext();
		}
	}
}
