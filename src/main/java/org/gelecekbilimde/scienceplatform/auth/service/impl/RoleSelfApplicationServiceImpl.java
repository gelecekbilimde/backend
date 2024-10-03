package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationAlreadyExistException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationNotFoundByUserIdAndStatusException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundByNameException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleApplicationRepository;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RoleSelfApplicationService;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RoleSelfApplicationServiceImpl implements RoleSelfApplicationService {

	private final UserRepository userRepository;
	private final RoleApplicationRepository roleApplicationRepository;
	private final RoleRepository roleRepository;
	private final Identity identity;


	@Override
	public void createAuthorApplication() {
		this.createApplication(RoleName.AUTHOR);
	}


	@Override
	public void createModeratorApplication() {
		this.createApplication(RoleName.MODERATOR);
	}


	private void createApplication(final RoleName roleName) {

		UserEntity user = userRepository.findById(identity.getUserId())
			.orElseThrow(() -> new UserNotFoundByIdException(identity.getUserId()));

		boolean existAnyApplicationInReview = roleApplicationRepository
			.existsByUserAndStatus(user, RoleApplicationStatus.IN_REVIEW);
		if (existAnyApplicationInReview) {
			throw new RoleApplicationAlreadyExistException();
		}

		RoleEntity role = roleRepository.findByName(roleName.name())
			.orElseThrow(() -> new RoleNotFoundByNameException(roleName.name()));

		RoleApplicationEntity application = RoleApplicationEntity.builder()
			.user(user)
			.role(role)
			.status(RoleApplicationStatus.IN_REVIEW)
			.build();
		roleApplicationRepository.save(application);
	}


	@Override
	public void cancel() {

		RoleApplicationEntity application = roleApplicationRepository
			.findByUserIdAndStatus(identity.getUserId(), RoleApplicationStatus.IN_REVIEW)
			.orElseThrow(() -> new RoleApplicationNotFoundByUserIdAndStatusException(
					identity.getUserId(),
					RoleApplicationStatus.IN_REVIEW
				)
			);

		application.cancel();
		roleApplicationRepository.save(application);
	}

}
