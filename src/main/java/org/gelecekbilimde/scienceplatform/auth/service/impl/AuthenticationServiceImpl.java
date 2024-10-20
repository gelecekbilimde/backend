package org.gelecekbilimde.scienceplatform.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByEmailException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotVerifiedException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserPasswordNotValidException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.auth.service.TokenService;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.port.UserReadPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

	private final UserReadPort userReadPort;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	private final InvalidTokenService invalidTokenService;
	private final Identity identity;


	@Override
	public Token login(LoginRequest request) {

		User user = userReadPort.findByEmail(request.getEmail())
			.orElseThrow(() -> new UserNotFoundByEmailException(request.getEmail()));

		if (!user.isVerified()) {
			throw new UserNotVerifiedException(request.getEmail());
		}

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new UserPasswordNotValidException();
		}

		final Claims claims = this.generateClaims(user);
		return tokenService.generate(claims);
	}


	@Override
	public Token refresh(RefreshRequest refreshRequest) {

		final String refreshToken = refreshRequest.getRefreshToken();
		final Claims payload = tokenService.getPayload(refreshToken);

		final String email = payload.get(TokenClaims.USER_MAIL.getValue(), String.class);
		final User user = userReadPort.findByEmail(email)
			.orElseThrow(() -> new UserNotFoundByEmailException(email));

		final Claims claims = this.generateClaims(user);

		return tokenService.generate(claims, refreshToken);
	}

	private Claims generateClaims(User user) {
		final ClaimsBuilder claimsBuilder = Jwts.claims();
		claimsBuilder.add(TokenClaims.USER_ID.getValue(), user.getId());
		claimsBuilder.add(TokenClaims.USER_FIRST_NAME.getValue(), user.getFirstName());
		claimsBuilder.add(TokenClaims.USER_LAST_NAME.getValue(), user.getLastName());
		claimsBuilder.add(TokenClaims.USER_STATUS.getValue(), user.getStatus());
		claimsBuilder.add(TokenClaims.USER_MAIL.getValue(), user.getEmail());
		claimsBuilder.add(TokenClaims.USER_ROLE.getValue(), user.getRole().getName());
		claimsBuilder.add(TokenClaims.USER_PERMISSIONS.getValue(), user.getRole().getPermissionNames());
		return claimsBuilder.build();
	}


	@Override
	public void logout(String refreshToken) {

		final String refreshTokenId = tokenService.getPayload(refreshToken).getId();
		final String tokenId = tokenService.getPayload(identity.getAccessToken()).getId();

		final List<String> invalidTokenIds = List.of(refreshTokenId, tokenId);
		invalidTokenService.saveAll(invalidTokenIds);
	}

}
