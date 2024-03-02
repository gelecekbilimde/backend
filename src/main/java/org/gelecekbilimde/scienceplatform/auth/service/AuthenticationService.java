package org.gelecekbilimde.scienceplatform.auth.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.auth.dto.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.request.UserVerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.dto.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.model.Permission;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.common.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.config.JwtService;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.exception.UserVerifyException;
import org.gelecekbilimde.scienceplatform.user.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.enums.Gender;
import org.gelecekbilimde.scienceplatform.user.enums.UserStatus;
import org.gelecekbilimde.scienceplatform.user.enums.UserVerificationStatus;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserVerification;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserVerificationRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final RoleRepository roleRepository;
	private final UserEmailService userEmailService;
	private final UserVerificationRepository userVerificationRepository;


	// todo : refactor edilecek
	public TokenResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new ClientException("This user is already registered");
		}

		Role role = roleRepository.getByIsDefaultTrue().orElseThrow(() -> new ServerException("Default Role is not defined."));
		List<String> scope = scopeList(role.getId());


		Gender gender = null;
		if (request.getGender() != null) {
			boolean isGenderExists = EnumSet.allOf(Gender.class).stream().anyMatch(e -> e.name().equals(request.getGender()));
			if (!isGenderExists) {
				throw new ClientException("gender type not found");
			}
			gender = Gender.valueOf(request.getGender());
		}

		Degree degree = null;
		if (request.getDegree() != null) {
			boolean isDegreeExists = EnumSet.allOf(Degree.class).stream().anyMatch(e -> e.name().equals(request.getDegree()));
			if (!isDegreeExists) {
				throw new ClientException("degree type not found");
			}
			degree = Degree.valueOf(request.getDegree());
		}


		User user = User.builder()
			.id(Util.generateUUID())
			.name(request.getFirstname())
			.lastName(request.getLastname())
			.email(request.getEmail())
			.birthDate(request.getBirthDate())
			.biography(request.getBiography())
			.gender(gender)
			.degree(degree)
			.role(role)
			.roleId(role.getId())
			.status(UserStatus.NOT_VERIFIED)
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		userRepository.save(user);


		CompletableFuture.runAsync(() -> {

			UserVerification userVerification = UserVerification.builder()
				.userId(user.getId())
				.status(UserVerificationStatus.WAITING)
				.build();
			userVerificationRepository.save(userVerification);

			userEmailService.sendVerifyMessage(user.getEmail(), userVerification.getId());
		});


		var jwtToken = jwtService.generateToken(user, scope);

		var refreshToken = jwtService.generateRefreshToken(user);

		return TokenResponse
			.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	public TokenResponse login(LoginRequest request) {
		try {

			User user = userRepository.findByEmail(request.getEmail())
				.filter(User::isVerified)
				.orElseThrow(() -> new UserNotFoundException("User not found: " + request.getEmail()));

			if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				throw new ClientException("Hatalı Eposta veya Şifre");
			}

			Role role = roleRepository.findById(user.getRoleId()).orElseThrow(() -> new ServerException("User Scope has a problem"));
			List<String> scope = scopeList(role.getId());

			var jwtToken = jwtService.generateToken(user, scope);
			var refreshToken = jwtService.generateRefreshToken(user);


			return TokenResponse
				.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();
		} catch (Exception e) {
			throw new ClientException(e.getMessage());
		}

	}

	public TokenResponse generateGuestToken() {
		var jwtToken = jwtService.generateGuestToken(JwtService.GUEST_USERNAME, scopeList(JwtService.GUEST_USERNAME));

		return TokenResponse
			.builder()
			.accessToken(jwtToken)
			.build();
	}

	public Object refreshToken(HttpServletRequest request) {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new ClientException("Token bilgisine ulaşılamadı");
		}
		refreshToken = authHeader.substring(7);

		final Claims claims = jwtService.extractAllClaims(refreshToken);

		final String userName = (String) claims.get(TokenClaims.SUBJECT.getValue());

		if (userName == null) {
			throw new ClientException("Kullanıcı bilgilerinde hata var");
		}

		final User user = this.userRepository.findByEmail(userName).orElseThrow(() -> new ClientException("Kullanıcı Bulunamadı"));

		Role role = roleRepository.findById(user.getRoleId())
			.orElseThrow(() -> new ServerException("User Scope has a problem"));
		List<String> scope = scopeList(role.getId());

		var jwtToken = jwtService.generateToken(user, scope);

		return TokenResponse
			.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	private List<String> scopeList(String roleId) {
		List<Permission> rolePermission = roleRepository.findPermissionsByRoleId(roleId);
		return rolePermission.stream().map(Permission::getName).toList();
	}

	@Transactional
	public void verify(UserVerifyRequest userVerifyRequest) {

		UserVerification userVerification = userVerificationRepository
			.findById(userVerifyRequest.getVerificationId())
			.orElseThrow(() -> new UserVerifyException("Verification ID is not valid!"));

		if (userVerification.isCompleted()) {
			throw new UserVerifyException("User verification is already completed!");
		}

		userVerification.complete();
		userVerificationRepository.save(userVerification);


		User user = userRepository.findById(userVerification.getUserId())
			.orElseThrow(() -> new UserNotFoundException("User not found!"));

		user.verify();
		userRepository.save(user);

		CompletableFuture.runAsync(() -> userEmailService.sendWelcomeMessage(user.getEmail()));
	}

}
