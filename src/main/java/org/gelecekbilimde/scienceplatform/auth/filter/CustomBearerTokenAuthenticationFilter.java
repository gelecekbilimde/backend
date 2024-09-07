package org.gelecekbilimde.scienceplatform.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.auth.service.TokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomBearerTokenAuthenticationFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final InvalidTokenService invalidTokenService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
									@NonNull HttpServletResponse httpServletResponse,
									@NonNull FilterChain filterChain)
		throws ServletException, IOException {

		final String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		if (Token.isBearerToken(authorizationHeader)) {

			final String jwt = Token.getJwt(authorizationHeader);

			tokenService.verifyAndValidate(jwt);

			final String tokenId = tokenService.getPayload(jwt).getId();
			invalidTokenService.checkForInvalidityOfToken(tokenId);

			final var authentication = tokenService.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
