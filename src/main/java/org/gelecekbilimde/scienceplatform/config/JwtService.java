package org.gelecekbilimde.scienceplatform.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
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

	public Object extractClaim(String token,String claim){
		final Claims claims = extractAllClaims(token);
		return claims.get(claim,Object.class);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String generateToken(User user, List<String> scope) {

		HashMap<String,Object> claim  = new HashMap<>();

		claim.put("fullName", user.getName() + " " + user.getLastname());
		claim.put("mail",user.getEmail());
		claim.put("userId",user.getId());
		claim.put("role",user.getRole().getRole());
		claim.put("scope",scope);


		return generateToken(claim, user);
	}

	public String generateToken(
		Map<String, Object> extraClaims,
		User user
	) {
		return buildToken(extraClaims, user.getUsername(), tokenExpiration);
	}


	public String generateRefreshToken(User user) {
		HashMap<String,Object> claim  = new HashMap<>();
		claim.put("role",user.getRole().getRole());

		return buildToken(claim, user.getUsername(), refreshExpiration );
	}


	public String generateGuestToken(String role, List<String> scope) {
		HashMap<String, Object> claim = new HashMap<>();
		claim.put("fullName", "Ziyaretci"); // todo : buraya bilim isnsanlarının isimlerini yazsak güzel olur
		claim.put("role", role);
		claim.put("scope", scope);
		return buildToken(claim, role, guestTokenExpiration);
	}

	private String buildToken(
		Map<String, Object> extraClaims,
		String subject,
		long expiration
	) {
		return Jwts
			.builder()
			.setHeaderParam("jti", UUID.randomUUID().toString())
			.setHeaderParam("typ","JWT")
			.setClaims(extraClaims)
			.setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.setIssuer("gelecekbilimde.net")
			.signWith(getSignInPrivateKey(), SignatureAlgorithm.RS256)
			.compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractSubject(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	public boolean isGuestTokenValid(String token) {
		return !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return  extractClaim(token,Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
			.parserBuilder()
			.setSigningKey(getSignInPublicKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}


	private PublicKey getSignInPublicKey() {
		try {
			byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
			String keyContent = new String(keyBytes);
			keyContent = keyContent.replaceAll("\\s+|-----BEGIN PUBLIC KEY-----|-----END PUBLIC KEY-----", "");

			byte[] keyData = Base64.getDecoder().decode(keyContent);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(new X509EncodedKeySpec(keyData));
		}catch (Exception e){
			throw new ServerException("Public Key Okunurken Hata oldu: " + e.getMessage());
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
		}catch (Exception e){
			throw new ServerException("Private Key Okunurken Hata oldu: " + e.getMessage());
		}

	}
}
