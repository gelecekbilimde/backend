package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundByNameException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserAlreadyRegisteredException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserVerificationAlreadyCompletedException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserVerificationIsNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;
import org.gelecekbilimde.scienceplatform.auth.model.request.RegisterRequest;
import org.gelecekbilimde.scienceplatform.auth.model.request.VerifyRequest;
import org.gelecekbilimde.scienceplatform.auth.port.RoleReadPort;
import org.gelecekbilimde.scienceplatform.auth.service.RegistrationService;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserVerification;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserStatus;
import org.gelecekbilimde.scienceplatform.user.model.enums.UserVerificationStatus;
import org.gelecekbilimde.scienceplatform.user.port.UserReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserSavePort;
import org.gelecekbilimde.scienceplatform.user.port.UserVerificationReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserVerificationSavePort;
import org.gelecekbilimde.scienceplatform.user.service.UserEmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
class RegistrationServiceImpl implements RegistrationService {

	private final UserReadPort userReadPort;
	private final UserSavePort userSavePort;
	private final RoleReadPort roleReadPort;
	private final PasswordEncoder passwordEncoder;
	private final UserEmailService userEmailService;
	private final UserVerificationReadPort userVerificationReadPort;
	private final UserVerificationSavePort userVerificationSavePort;

	@Override
	@Transactional
	public void register(RegisterRequest request) {

		if (userReadPort.existsByEmail(request.getEmail())) {
			throw new UserAlreadyRegisteredException(request.getEmail());
		}

		Role role = roleReadPort.findByName(RoleName.USER)
			.orElseThrow(() -> new RoleNotFoundByNameException(RoleName.USER.name()));

		User user = User.builder()
			.firstName(request.getFirstname())
			.lastName(request.getLastname())
			.email(request.getEmail())
			.birthDate(request.getBirthDate())
			.biography(request.getBiography())
			.gender(request.getGender())
			.degree(request.getDegree())
			.role(role)
			.status(UserStatus.NOT_VERIFIED)
			.password(passwordEncoder.encode(request.getPassword()))
			.build();

		User savedUser = userSavePort.save(user);

		UserVerification userVerification = UserVerification.builder()
			.user(savedUser)
			.status(UserVerificationStatus.WAITING)
			.build();
		userVerificationSavePort.save(userVerification);

		CompletableFuture.runAsync(
			() -> userEmailService
				.sendVerifyMessage(savedUser.getEmail(), userVerification.getId())
		);
	}

	@Override
	@Transactional
	public void verify(VerifyRequest verifyRequest) {

		UserVerification userVerification = userVerificationReadPort
			.findById(verifyRequest.getVerificationId())
			.orElseThrow(() -> new UserVerificationIsNotFoundException(verifyRequest.getVerificationId()));

		if (userVerification.isCompleted()) {
			throw new UserVerificationAlreadyCompletedException();
		}

		userVerification.complete();
		userVerificationSavePort.save(userVerification);


		User user = userReadPort.findById(userVerification.getUser().getId())
			.orElseThrow(() -> new UserNotFoundByIdException(userVerification.getUser().getId()));

		user.verify();
		userSavePort.save(user);

		CompletableFuture.runAsync(() -> userEmailService.sendWelcomeMessage(user.getEmail()));
	}

}
