package org.gelecekbilimde.scienceplatform.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.gelecekbilimde.scienceplatform.auth.config.TokenConfiguration;
import org.gelecekbilimde.scienceplatform.auth.exception.TokenNotValidException;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.auth.service.TokenService;
import org.gelecekbilimde.scienceplatform.common.util.ListUtil;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class TokenServiceImpl implements TokenService {

	private final TokenConfiguration tokenConfiguration;

	@Override
	public Token generate(Claims claims) {

		final long currentTimeMillis = System.currentTimeMillis();

		final JwtBuilder tokenBuilder = this.initializeTokenBuilder(currentTimeMillis);

		final Date accessTokenExpiresAt = DateUtils.addMinutes(
			new Date(currentTimeMillis), tokenConfiguration.getTokenExpiration()
		);
		final String accessToken = tokenBuilder
			.id(RandomUtil.generateUUID())
			.expiration(accessTokenExpiresAt)
			.claims(claims)
			.compact();

		final Date refreshTokenExpiresAt = DateUtils.addDays(
			new Date(currentTimeMillis), tokenConfiguration.getRefreshExpiration()
		);
		final String refreshToken = tokenBuilder
			.id(RandomUtil.generateUUID())
			.expiration(refreshTokenExpiresAt)
			.claims(claims)
			.compact();

		return Token.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}


	@Override
	public Token generate(Claims claims, String refreshToken) {

		final long currentTimeMillis = System.currentTimeMillis();

		final JwtBuilder tokenBuilder = this.initializeTokenBuilder(currentTimeMillis);

		final Date accessTokenExpiresAt = DateUtils.addMinutes(
			new Date(currentTimeMillis), tokenConfiguration.getTokenExpiration()
		);
		final String accessToken = tokenBuilder
			.id(RandomUtil.generateUUID())
			.expiration(accessTokenExpiresAt)
			.claims(claims)
			.compact();

		return Token.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	private JwtBuilder initializeTokenBuilder(long currentTimeMillis) {
		return Jwts.builder()
			.header()
			.type(OAuth2AccessToken.TokenType.BEARER.getValue())
			.and()
			.issuer(tokenConfiguration.getIssuer())
			.issuedAt(new Date(currentTimeMillis))
			.signWith(tokenConfiguration.getPrivateKey());
	}


	@Override
	public void verifyAndValidate(String token) {
		try {
			final Jws<Claims> claims = Jwts.parser()
				.verifyWith(tokenConfiguration.getPublicKey())
				.build()
				.parseSignedClaims(token);

			final JwsHeader header = claims.getHeader();
			if (!OAuth2AccessToken.TokenType.BEARER.getValue().equals(header.getType())) {
				throw new RequiredTypeException(token);
			}

			if (!Jwts.SIG.RS256.getId().equals(header.getAlgorithm())) {
				throw new SignatureException(token);
			}

		} catch (MalformedJwtException | ExpiredJwtException | SignatureException | RequiredTypeException exception) {
			throw new TokenNotValidException();
		}
	}


	@Override
	public Claims getPayload(String token) {
		return Jwts.parser()
			.verifyWith(tokenConfiguration.getPublicKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
	}


	@Override
	public UsernamePasswordAuthenticationToken getAuthentication(String token) {

		Jws<Claims> claims = Jwts.parser()
			.verifyWith(tokenConfiguration.getPublicKey())
			.build()
			.parseSignedClaims(token);

		JwsHeader header = claims.getHeader();
		Claims payload = claims.getPayload();

		final Jwt jwt = new Jwt(
			token,
			payload.getIssuedAt().toInstant(),
			payload.getExpiration().toInstant(),
			Map.of(
				TokenClaims.TYPE.getValue(), header.getType(),
				TokenClaims.ALGORITHM.getValue(), header.getAlgorithm()
			),
			payload
		);

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		final List<String> permissions = ListUtil.to(payload.get(TokenClaims.USER_PERMISSIONS.getValue()), String.class);
		permissions.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));

		return UsernamePasswordAuthenticationToken.authenticated(jwt, null, authorities);
	}

}
