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
import org.gelecekbilimde.scienceplatform.util.MessagesUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private final MessagesUtil messagesUtil = new MessagesUtil();

	@Transactional
	public TokenDto register(String language, RegisterDto request) {


		if (blackListRepository.existsByEmail(request.getEmail())){
			throw new ClientException(messagesUtil.getMessage("message_black_list", language));
		}

		if (userRepository.existsByEmail(request.getEmail())){
			throw new ClientException(messagesUtil.getMessage("message_already_registered", language));
		}
		try {

			Role role = roleRepository.getByIsDefaultTrue().orElseThrow(()-> new ServerException(messagesUtil.getMessage("message_default_role", language)));
			List<String> scope = scopeList(role.getRole());


			Date birthDate = null;
			if (request.getBirthDate() != null){
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				birthDate = formatter.parse(request.getBirthDate());
			}

			Gender gender = null;
			if (request.getGender() != null) {
				boolean isGenderExists = EnumSet.allOf(Gender.class).stream().anyMatch(e -> e.name().equals(request.getGender()));
				if (!isGenderExists) {
					throw new ClientException(messagesUtil.getMessage("gender_type_not_found", language));
				}
				gender = Gender.valueOf(request.getGender());
			}

			Degree degree = null;
			if (request.getDegree() != null){
				boolean isDegreeExists = EnumSet.allOf(Degree.class).stream().anyMatch(e -> e.name().equals(request.getDegree()));
				if (!isDegreeExists) {
					throw new ClientException(messagesUtil.getMessage("degree_type_not_found", language));
				}
				degree = Degree.valueOf(request.getDegree());
			}

			var user = User.builder()
				.name(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.birthDate(birthDate)
				.biography(request.getBiography())
				.gender(gender)
				.degree(degree)
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
		}catch (ParseException parseException){
			throw new ClientException(messagesUtil.getMessage("message_birthDate_wrong", language));
		}catch (Exception exception){
			throw new ClientException(exception.getMessage());
		}
	}

	public TokenDto login(String language, LoginDto request) {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException(messagesUtil.getMessage("user_not_found", language) + ": " + request.getEmail()));

			if (blackListRepository.existsByEmail(user.getEmail())){
				throw new ClientException(messagesUtil.getMessage("message_black_list", language));
			}

			if (!user.isEnabled()){
				throw new ClientException(messagesUtil.getMessage("this_user_not_enabled", language));
			}

			if (user.isUserLock()){
				throw new ClientException(messagesUtil.getMessage("this_userlock", language));
			}

			if (!user.isEmailVerify()){
				throw new ClientException(messagesUtil.getMessage("this_email_not_verify", language));
			}

			Role role = roleRepository.findByRole(user.getRole().getRole()).orElseThrow(() -> new ServerException(messagesUtil.getMessage("user_scope_has_a_problem", language)));
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

	public Object refreshToken(String language, HttpServletRequest request)  {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;

		if (authHeader == null ||!authHeader.startsWith(TokenType.BEARER.name() +  " ")) {
			throw new ClientException(messagesUtil.getMessage("token_information_could_not_be_found", language));
		}
		refreshToken = authHeader.substring(7);

		userEmail = jwtService.extractSubject(refreshToken);

		if (userEmail == null) {
			throw new ClientException(messagesUtil.getMessage("there_is_an_error_in_the_user_information", language));
		}

		var user = this.userRepository.findByEmail(userEmail).orElseThrow();

		if (!jwtService.isTokenValid(refreshToken, user)) {
			throw new ClientException(messagesUtil.getMessage("there_is_an_error_in_the_session_information", language));
		}

		List<String> emptyScope = new ArrayList<>();
		var jwtToken = jwtService.generateToken(user,emptyScope);

		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);

		return TokenDto
			.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	private List<String> scopeList(String role) {
		List<Permission> rolePermission = roleRepository.findPermissionsByRole(role);
		return rolePermission.stream().map(Permission::getPermission).toList();
	}

}
