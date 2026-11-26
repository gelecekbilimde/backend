package org.gelecekbilimde.scienceplatform;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateUtils;
import org.gelecekbilimde.scienceplatform.auth.config.TokenConfiguration;
import org.gelecekbilimde.scienceplatform.auth.model.Token;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public abstract class EndToEndTest extends TestContainerConfiguration {

	@Autowired
	protected GBMockMvc gbMockMvc;

	protected Token superAdminToken;
	protected Token adminToken;
	protected Token userToken;


	@Autowired
	private TokenConfiguration tokenConfiguration;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	@SuppressWarnings("all")
	protected void setUp() {
		/*final Optional<User> superAdmin = userRepository.findById(AysValidTestData.SuperAdmin.ID);
		final Claims claimsOfMockSuperAdminToken = superAdmin.get().getClaims();
		this.superAdminToken = this.generate(claimsOfMockSuperAdminToken);

		final Optional<User> admin = userRepository.findById(AysValidTestData.Admin.ID);
		final Claims claimsOfMockAdminToken = admin.get().getClaims();
		this.adminToken = this.generate(claimsOfMockAdminToken);

		final Optional<User> user = userRepository.findById(AysValidTestData.SuperAdmin.ID);
		final Claims claimsOfMockUserToken = user.get().getClaims();
		this.userToken = this.generate(claimsOfMockUserToken);*/
	}


	protected Token generate(Claims claims) {
		final long currentTimeMillis = System.currentTimeMillis();

		final Date tokenIssuedAt = new Date(currentTimeMillis);

		// TODO: [alpemreelmas] [28.09.2024] Refresh token expiration time should be taken from the configuration
		final Date accessTokenExpiresAt = DateUtils.addMinutes(new Date(currentTimeMillis), 1);
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

		// TODO: [alpemreelmas] [28.09.2024] Refresh token expiration time should be taken from the configuration
		final Date refreshTokenExpiresAt = DateUtils.addDays(new Date(currentTimeMillis), 1);
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
