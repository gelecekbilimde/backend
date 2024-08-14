package org.gelecekbilimde.scienceplatform.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.config.TokenConfiguration;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.common.exception.ClientException;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService { // TODO : interface yazılmalı

	private final TokenConfiguration tokenConfiguration;

	public String generateToken(UserEntity userEntity, List<String> scope) {

		HashMap<String, Object> claim = new HashMap<>();

		final RoleEntity roleEntity = userEntity.getRoleEntity();

		claim.put(TokenClaims.USER_ID.getValue(), userEntity.getId());
		claim.put(TokenClaims.FULL_NAME.getValue(), userEntity.getName() + " " + userEntity.getLastName());
		claim.put(TokenClaims.MAIL.getValue(), userEntity.getEmail());
		claim.put(TokenClaims.ROLE_NAME.getValue(), roleEntity.getName());
		claim.put(TokenClaims.ROLE_ID.getValue(), roleEntity.getId());
		claim.put(TokenClaims.USER_STATUS.getValue(), userEntity.getStatus());
		claim.put(TokenClaims.SCOPE.getValue(), scope);


		return generateToken(claim, userEntity);
	}

	public String generateToken(
		Map<String, Object> extraClaims,
		UserEntity userEntity
	) {
		return buildToken(extraClaims, userEntity.getUsername(), tokenConfiguration.getTokenExpiration());
	}


	public String generateRefreshToken(UserEntity userEntity) {
		HashMap<String, Object> claim = new HashMap<>();
		claim.put(TokenClaims.ROLE_ID.getValue(), userEntity.getRoleId());

		return buildToken(claim, userEntity.getUsername(), tokenConfiguration.getRefreshExpiration());
	}


	public String generateGuestToken(String role, List<String> scope) {
		HashMap<String, Object> claim = new HashMap<>();
		claim.put(TokenClaims.FULL_NAME.getValue(), TokenClaims.GUEST_FULL_NAME.getValue());
		claim.put(TokenClaims.ROLE_NAME.getValue(), role);
		claim.put(TokenClaims.SCOPE.getValue(), scope);
		return buildToken(claim, role, tokenConfiguration.getGuestTokenExpiration());
	}

	private String buildToken(
		Map<String, Object> extraClaims,
		String subject,
		long expiration
	) {
		return Jwts
			.builder()
			.setHeaderParam(TokenClaims.JWT_ID.getValue(), RandomUtil.generateUUID())
			.setHeaderParam(TokenClaims.TYPE.getValue(), TokenClaims.TYPE_VAL.getValue())
			.setClaims(extraClaims)
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.setIssuer(TokenClaims.ISSUER.getValue())
			.signWith(tokenConfiguration.getPrivateKey(), SignatureAlgorithm.RS256)
			.compact();
	}

	public Claims extractAllClaims(String token) {
		try {
			return Jwts
				.parserBuilder()
				.setSigningKey(tokenConfiguration.getPublicKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (MalformedJwtException | ExpiredJwtException | SignatureException exception) {
			throw new ClientException("Token is not valid: " + exception.getMessage());
		}
	}

}
