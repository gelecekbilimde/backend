package org.gelecekbilimde.scienceplatform.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.config.JwtService;
import org.gelecekbilimde.scienceplatform.dto.LoginDto;
import org.gelecekbilimde.scienceplatform.dto.TokenDto;
import org.gelecekbilimde.scienceplatform.dto.RegisterDto;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.model.Permission;
import org.gelecekbilimde.scienceplatform.model.Role;
import org.gelecekbilimde.scienceplatform.model.Token;
import org.gelecekbilimde.scienceplatform.model.enums.Degree;
import org.gelecekbilimde.scienceplatform.model.enums.Gender;
import org.gelecekbilimde.scienceplatform.model.enums.TokenType;
import org.gelecekbilimde.scienceplatform.repository.BlackListRepository;
import org.gelecekbilimde.scienceplatform.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.repository.TokenRepository;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;
	private final BlackListRepository blackListRepository;

	public TokenDto register(RegisterDto request) {


		if (blackListRepository.existsByEmail(request.getEmail())){
			throw new ClientException("This user is black list registered");
		}

		if (userRepository.existsByEmail(request.getEmail())){
			throw new ClientException("This user is already registered");
		}

		Role role = roleRepository.getByIsDefaultTrue().orElseThrow(()-> new ServerException("Default Role is not defined."));
		List<String> scope = scopeList(role.getRole());

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date birthDate = null;
		try {
			birthDate = formatter.parse(request.getBirthDate());
		} catch (ParseException e) {
			throw new ClientException("BirthDate format is wrong. dd/MM/yyyy");
		}

		boolean isGenderExists = EnumSet.allOf(Gender.class).stream().anyMatch(e -> e.name().equals(request.getGender()));

		if (!isGenderExists) {
			throw new ClientException("gender type not found");
		}

		boolean isDegreeExists = EnumSet.allOf(Degree.class).stream().anyMatch(e -> e.name().equals(request.getDegree()));
		if (!isDegreeExists) {
			throw new ClientException("degree type not found");
		}

		var user = User.builder()
			.name(request.getFirstname())
			.lastname(request.getLastname())
			.email(request.getEmail())
			.birthDate(birthDate)
			.biography(request.getBiography())
			.gender(Gender.valueOf(request.getGender()))
			.degree(Degree.valueOf(request.getDegree()))
			.role(role)
			.userEnable(true).userLock(false).emailVerify(false)
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		var savedUser = userRepository.save(user);

		var jwtToken = jwtService.generateToken(user,scope);

		saveUserToken(savedUser, jwtToken);

		var refreshToken = jwtService.generateRefreshToken(user);

		return TokenDto
			.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	public TokenDto login(LoginDto request) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found: " + request.getEmail()));

			if (blackListRepository.existsByEmail(user.getEmail())){
				throw new ClientException("This user is black list registered");
			}

			if (!user.isEnabled()){
				throw new ClientException("This User Not Enabled");
			}

			if (user.isUserLock()){
				throw new ClientException("This UserLock");
			}

			if (!user.isEmailVerify()){
				throw new ClientException("This Email Not Verify");
			}

			Role role = roleRepository.findByRole(user.getRole().getRole()).orElseThrow(() -> new ServerException("User Scope has a problem"));
			List<String> scope = scopeList(role.getRole());

			var jwtToken = jwtService.generateToken(user,scope);
			var refreshToken = jwtService.generateRefreshToken(user);

			revokeAllUserTokens(user);
			saveUserToken(user,jwtToken);

			return TokenDto
				.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();

		} catch (Exception e) {
			throw new ServerException(e.getMessage());
		}

	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});

		tokenRepository.saveAll(validUserTokens);
	}

	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder()
			.user(user)
			.token(jwtToken)
			.tokenType(TokenType.BEARER)
			.revoked(false)
			.expired(false)
			.build();

		// ayrıca redisede yazabiliriz
		tokenRepository.save(token);
	}

	public TokenDto generateGuestToken(){
		var jwtToken = jwtService.generateGuestToken(jwtService.GUEST_USERNAME, scopeList(jwtService.GUEST_USERNAME));

		return TokenDto
			.builder()
			.accessToken(jwtToken)
			.build();
	}

	public Object refreshToken(HttpServletRequest request)  {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;

		if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
			throw new ClientException("Token bilgisine ulaşılamadı");
		}
		refreshToken = authHeader.substring(7);

		userEmail = jwtService.extractSubject(refreshToken);

		if (userEmail == null) {
			throw new ClientException("Kullanıcı bilgilerinde hata var");
		}

		var user = this.userRepository.findByEmail(userEmail).orElseThrow();

		if (!jwtService.isTokenValid(refreshToken, user)) {
			throw new ClientException("Oturum bilgisinde hata var");
		}

		List<String> emptyScope = new ArrayList<>();
		var accessToken = jwtService.generateToken(user,emptyScope);

		revokeAllUserTokens(user);
		saveUserToken(user, accessToken);

		return TokenDto.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	private List<String> scopeList(String role) {
		List<Permission> rolePermission =roleRepository.findPermissionsByRole(role);
		return rolePermission.stream().map(Permission::getPermission).toList();
	}

}
