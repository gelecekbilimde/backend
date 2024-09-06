package org.gelecekbilimde.scienceplatform.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.VerifyException;
import org.gelecekbilimde.scienceplatform.auth.exception.WrongEmailOrPasswordException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.auth.service.TokenService;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;
	private final InvalidTokenService invalidTokenService;
	private final Identity identity;

	@Override
	public Token login(LoginRequest request) {

		UserEntity userEntity = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new UserNotFoundException(request.getEmail()));

		if (!userEntity.isVerified()) {
			throw new VerifyException(request.getEmail());
		}

		if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
			throw new WrongEmailOrPasswordException();
		}

		final Claims claims = this.getClaimsBuilder(userEntity);
		return tokenService.generate(claims);
	}

	@Override
	public Token refresh(RefreshRequest refreshRequest) {

		final String refreshToken = refreshRequest.getRefreshToken();
		final Claims payload = tokenService.getPayload(refreshToken);

		final String email = payload.get(TokenClaims.USER_MAIL.getValue(), String.class);
		final UserEntity userEntity = this.userRepository.findByEmail(email)
			.orElseThrow(() -> new UserNotFoundException(email));

		final Claims claims = this.getClaimsBuilder(userEntity);

		return tokenService.generate(claims, refreshToken);
	}

	private Claims getClaimsBuilder(UserEntity userEntity) {
		RoleEntity roleEntity = userEntity.getRoleEntity();

		final ClaimsBuilder claimsBuilder = Jwts.claims();
		claimsBuilder.add(TokenClaims.USER_ID.getValue(), userEntity.getId());
		claimsBuilder.add(TokenClaims.USER_FIRST_NAME.getValue(), userEntity.getFirstName());
		claimsBuilder.add(TokenClaims.USER_LAST_NAME.getValue(), userEntity.getLastName());
		claimsBuilder.add(TokenClaims.USER_STATUS.getValue(), userEntity.getStatus());
		claimsBuilder.add(TokenClaims.USER_MAIL.getValue(), userEntity.getEmail());
		claimsBuilder.add(TokenClaims.USER_ROLE.getValue(), roleEntity.getName());
		claimsBuilder.add(TokenClaims.USER_PERMISSIONS.getValue(), roleEntity.getPermissionNames());
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
