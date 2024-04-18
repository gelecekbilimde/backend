package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.auth.service.LogoutService;
import org.gelecekbilimde.scienceplatform.config.JwtService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LogoutServiceImpl implements LogoutService {

	private final InvalidTokenService invalidTokenService;
	private final JwtService jwtService;
	private final Identity identity;

	@Override
	public void logout(String refreshToken) {
		final String refreshTokenId = jwtService.extractAllClaims(refreshToken).getId();
		invalidTokenService.saveInvalidToken(refreshTokenId);

		final String accessTokenId = identity.getAccessToken();
		final String tokenId = jwtService.extractAllClaims(accessTokenId).getId();
		invalidTokenService.saveInvalidToken(tokenId);
	}

}
