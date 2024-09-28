package org.gelecekbilimde.scienceplatform;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateUtils;
import org.gelecekbilimde.scienceplatform.auth.config.TokenConfiguration;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.util.ValidTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public abstract class RestControllerTest extends TestContainerConfiguration {

	@Autowired
	protected GBMockMvc mockMvc;

	@Autowired
	protected UserRepository userRepository;


	protected Token mockSuperAdminToken;
	protected Token mockAdminToken;
	protected Token mockUserToken;


	@Autowired
	private TokenConfiguration tokenConfiguration;

	@BeforeEach
	@SuppressWarnings("OptionalGetWithoutIsPresent disabled because of the test data is valid")
	public void initializeAuth() {
		// TODO: [alpemreelmas] [28.09.2024] This function should be rewritten according requirements of gelecek bilimde
		/*
		final Optional<UserEntity> superAdmin = userRepository.findById(ValidTestData.admin.ID);
		final Claims claimsOfMockSuperAdminToken = superAdmin.get().getClaims();
		this.mockSuperAdminToken = this.generate(claimsOfMockSuperAdminToken);

		final Optional<UserEntity> admin = userRepository.findById(ValidTestData.Admin.ID);
		final Claims claimsOfMockAdminToken = admin.get().getClaims();
		this.mockAdminToken = this.generate(claimsOfMockAdminToken);

		final Optional<UserEntity> user = userRepository.findById(ValidTestData.User.ID);
		final Claims claimsOfMockUserToken = user.get().getClaims();
		this.mockUserToken = this.generate(claimsOfMockUserToken);*/
	}

	private Token generate(Map<String, Object> claims) {
		final long currentTimeMillis = System.currentTimeMillis();

		final Date tokenIssuedAt = new Date(currentTimeMillis);

		final Date accessTokenExpiresAt = DateUtils.addMinutes(new Date(currentTimeMillis), 60);
		final String accessToken = Jwts.builder()
			.header()
			.add(TokenClaims.TYPE.getValue(), OAuth2AccessToken.TokenType.BEARER.getValue())
			.and()
			.id(RandomUtil.generateUUID())
			.issuer(tokenConfiguration.getIssuer())
			.issuedAt(tokenIssuedAt)
			.expiration(accessTokenExpiresAt)
			.signWith(tokenConfiguration.getPrivateKey())
			.claims(claims)
			.compact();

		final Date refreshTokenExpiresAt = DateUtils.addDays(new Date(currentTimeMillis), 60);
		final JwtBuilder refreshTokenBuilder = Jwts.builder();
		final String refreshToken = refreshTokenBuilder
			.header()
			.add(TokenClaims.TYPE.getValue(), OAuth2AccessToken.TokenType.BEARER.getValue())
			.and()
			.id(RandomUtil.generateUUID())
			.issuer(tokenConfiguration.getIssuer())
			.issuedAt(tokenIssuedAt)
			.expiration(refreshTokenExpiresAt)
			.signWith(tokenConfiguration.getPrivateKey())
			.claim(TokenClaims.USER_ID.getValue(), claims.get(TokenClaims.USER_ID.getValue()))
			.compact();

		return Token.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

}
