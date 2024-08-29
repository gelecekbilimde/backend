package org.gelecekbilimde.scienceplatform.auth.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.AlreadyRegisteredException;
import org.gelecekbilimde.scienceplatform.auth.exception.DefaultRoleNotDefinedException;
import org.gelecekbilimde.scienceplatform.auth.exception.DegreeTypeNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.GenderTypeNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserScopeException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserVerificationAlreadyCompletedException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserVerificationIsNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.VerifyException;
import org.gelecekbilimde.scienceplatform.auth.exception.WrongEmailOrPasswordException;
import org.gelecekbilimde.scienceplatform.auth.model.entity.PermissionEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.TokenClaims;
import org.gelecekbilimde.scienceplatform.auth.model.request.LoginRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.TokenRefreshRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.UserVerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.TokenResponse;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.AuthenticationService;
import org.gelecekbilimde.scienceplatform.common.exception.ClientException;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserVerificationEntity;
import org.gelecekbilimde.scienceplatform.user.model.enums.Degree;
import org.gelecekbilimde.scienceplatform.user.model.enums.Gender;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserStatus;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserVerificationStatus;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserVerificationRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final RoleRepository roleRepository;
	private final UserEmailService userEmailService;
	private final UserVerificationRepository userVerificationRepository;

	@Override
	public TokenResponse register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new AlreadyRegisteredException(request.getEmail());
		}

		RoleEntity roleEntity = roleRepository.getByIsDefaultTrue().orElseThrow(DefaultRoleNotDefinedException::new);
		List<String> scope = scopeList(roleEntity.getId());


		Gender gender = null;
		if (request.getGender() != null) {
			boolean isGenderExists = EnumSet.allOf(Gender.class).stream().anyMatch(e -> e.name().equals(request.getGender()));
			if (!isGenderExists) {
				throw new GenderTypeNotFoundException();
			}
			gender = Gender.valueOf(request.getGender());
		}

		Degree degree = null;
		if (request.getDegree() != null) {
			boolean isDegreeExists = EnumSet.allOf(Degree.class).stream().anyMatch(e -> e.name().equals(request.getDegree()));
			if (!isDegreeExists) {
				throw new DegreeTypeNotFoundException();
			}
			degree = Degree.valueOf(request.getDegree());
		}


		UserEntity userEntity = UserEntity.builder()
			.id(RandomUtil.generateUUID())
			.name(request.getFirstname())
			.lastName(request.getLastname())
			.email(request.getEmail())
			.birthDate(request.getBirthDate())
			.biography(request.getBiography())
			.gender(gender)
			.degree(degree)
			.roleEntity(roleEntity)
			.roleId(roleEntity.getId())
			.status(UserStatus.NOT_VERIFIED)
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		userRepository.save(userEntity);


		CompletableFuture.runAsync(() -> {

			UserVerificationEntity userVerificationEntity = UserVerificationEntity.builder()
				.userId(userEntity.getId())
				.status(UserVerificationStatus.WAITING)
				.build();
			userVerificationRepository.save(userVerificationEntity);

			userEmailService.sendVerifyMessage(userEntity.getEmail(), userVerificationEntity.getId());
		});


		var jwtToken = jwtService.generateToken(userEntity, scope);

		var refreshToken = jwtService.generateRefreshToken(userEntity);

		return TokenResponse
			.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	@Override
	public TokenResponse login(LoginRequest request) {
		try {

			UserEntity userEntity = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UserNotFoundException(request.getEmail()));
			if (!userEntity.isVerified()){
				throw new VerifyException(request.getEmail());
			}

			if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
				throw new WrongEmailOrPasswordException();
			}

			RoleEntity roleEntity = roleRepository.findById(userEntity.getRoleId()).orElseThrow(UserScopeException::new);
			List<String> scope = scopeList(roleEntity.getId());

			var jwtToken = jwtService.generateToken(userEntity, scope);
			var refreshToken = jwtService.generateRefreshToken(userEntity);


			return TokenResponse
				.builder()
				.accessToken(jwtToken)
				.refreshToken(refreshToken)
				.build();
		} catch (Exception e) {
			throw new ClientException(e.getMessage());
		}

	}

	@Override
	public TokenResponse generateGuestToken() {
		var jwtToken = jwtService.generateGuestToken("GUEST", scopeList("GUEST"));

		return TokenResponse
			.builder()
			.accessToken(jwtToken)
			.build();
	}

	@Override
	public TokenResponse refreshToken(TokenRefreshRequest refreshRequest) {

		final String refreshToken = refreshRequest.getRefreshToken();
		final Claims claims = jwtService.extractAllClaims(refreshToken);

		final String username = (String) claims.get(TokenClaims.SUBJECT.getValue());
		final UserEntity userEntity = this.userRepository.findByEmail(username)
			.orElseThrow(() -> new UserNotFoundException(username));

		RoleEntity roleEntity = roleRepository.findById(userEntity.getRoleId())
			.orElseThrow(() -> new RoleNotFoundException(userEntity.getRoleId()));
		List<String> scope = scopeList(roleEntity.getId());

		var jwtToken = jwtService.generateToken(userEntity, scope);

		return TokenResponse
			.builder()
			.accessToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	private List<String> scopeList(String roleId) {
		List<PermissionEntity> rolePermissionEntity = roleRepository.findPermissionsByRoleId(roleId);
		return rolePermissionEntity.stream().map(PermissionEntity::getName).toList();
	}

	@Transactional
	@Override
	public void verify(UserVerifyRequest userVerifyRequest) {

		UserVerificationEntity userVerificationEntity = userVerificationRepository
			.findById(userVerifyRequest.getVerificationId())
			.orElseThrow(() -> new UserVerificationIsNotFoundException(userVerifyRequest.getVerificationId()));

		if (userVerificationEntity.isCompleted()) {
			throw new UserVerificationAlreadyCompletedException();
		}

		userVerificationEntity.complete();
		userVerificationRepository.save(userVerificationEntity);


		UserEntity userEntity = userRepository.findById(userVerificationEntity.getUserId())
			.orElseThrow(UserNotFoundException::new);

		userEntity.verify();
		userRepository.save(userEntity);

		CompletableFuture.runAsync(() -> userEmailService.sendWelcomeMessage(userEntity.getEmail()));
	}

}
