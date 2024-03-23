package org.gelecekbilimde.scienceplatform.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.common.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

	@Value("${application.security.jwt.expiration}")
	private long tokenExpiration;
	@Value("${application.security.jwt.refresh-token.expiration}")
	private long refreshExpiration;
	@Value("${application.security.jwt.guest-token.expiration}")
	private Long guestTokenExpiration;
	@Value("${application.security.jwt.private-key}")
	private String privateKeyPath;
	@Value("${application.security.jwt.public-key}")
	private String publicKeyPath;

	public static final String GUEST_USERNAME = "GUEST";

	public String extractSubject(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Object extractClaim(String token, String claim) {
		final Claims claims = extractAllClaims(token);
		return claims.get(claim, Object.class);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(User user, List<String> scope) {

		HashMap<String, Object> claim = new HashMap<>();

		final Role role = user.getRole();

		claim.put(TokenClaims.USER_ID.getValue(), user.getId());
		claim.put(TokenClaims.FULL_NAME.getValue(), user.getName() + " " + user.getLastName());
		claim.put(TokenClaims.MAIL.getValue(), user.getEmail());
		claim.put(TokenClaims.ROLE_NAME.getValue(), role.getName());
		claim.put(TokenClaims.ROLE_ID.getValue(), role.getId());
		claim.put(TokenClaims.USER_STATUS.getValue(), user.getStatus());
		claim.put(TokenClaims.SCOPE.getValue(), scope);


		return generateToken(claim, user);
	}

	public String generateToken(
		Map<String, Object> extraClaims,
		User user
	) {
		return buildToken(extraClaims, user.getUsername(), tokenExpiration);
	}


	public String generateRefreshToken(User user) {
		HashMap<String, Object> claim = new HashMap<>();
		claim.put(TokenClaims.ROLE_ID.getValue(), user.getRoleId());

		return buildToken(claim, user.getUsername(), refreshExpiration);
	}


	public String generateGuestToken(String role, List<String> scope) {
		HashMap<String, Object> claim = new HashMap<>();
		claim.put(TokenClaims.FULL_NAME.getValue(), TokenClaims.GUEST_FULL_NAME.getValue());
		claim.put(TokenClaims.ROLE_NAME.getValue(), role);
		claim.put(TokenClaims.SCOPE.getValue(), scope);
		return buildToken(claim, role, guestTokenExpiration);
	}

	private String buildToken(
		Map<String, Object> extraClaims,
		String subject,
		long expiration
	) {
		return Jwts
			.builder()
			.setHeaderParam(TokenClaims.JWT_ID.getValue(), Util.generateUUID())
			.setHeaderParam(TokenClaims.TYPE.getValue(), TokenClaims.TYPE_VAL.getValue())
			.setClaims(extraClaims)
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.setIssuer(TokenClaims.ISSUER.getValue())
			.signWith(getSignInPrivateKey(), SignatureAlgorithm.RS256)
			.compact();
	}

	public Claims extractAllClaims(String token) {
		try {
			return Jwts
				.parserBuilder()
				.setSigningKey(getSignInPublicKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (MalformedJwtException | ExpiredJwtException | SignatureException exception) {
			throw new ClientException("Token is not valid: " + exception.getMessage());
		}
	}


	private PublicKey getSignInPublicKey() {
		try {
			byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
			String keyContent = new String(keyBytes);
			keyContent = keyContent.replaceAll("\\s+|-----BEGIN PUBLIC KEY-----|-----END PUBLIC KEY-----", "");

			byte[] keyData = Base64.getDecoder().decode(keyContent);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(new X509EncodedKeySpec(keyData));
		} catch (Exception e) {
			throw new ServerException("Public Key read error: " + e.getMessage());
		}
	}

	private PrivateKey getSignInPrivateKey() {
		try {
			byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
			String keyContent = new String(keyBytes);
			keyContent = keyContent.replaceAll("\\s+|-----BEGIN PRIVATE KEY-----|-----END PRIVATE KEY-----", "");

			byte[] keyData = Base64.getDecoder().decode(keyContent);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyData));
		} catch (Exception e) {
			throw new ServerException("Private Key read error: " + e.getMessage());
		}

	}
}
