package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationAlreadyExistException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationNotFoundByUserIdAndStatusException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundByNameException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplicationFilter;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleApplicationListRequest;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleApplicationRepository;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RoleSelfApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class RoleSelfApplicationServiceImpl implements RoleSelfApplicationService {

	private final RoleApplicationRepository roleApplicationRepository;
	private final RoleRepository roleRepository;
	private final Identity identity;


	private final RoleApplicationEntityToDomainMapper roleApplicationEntityToDomainMapper = RoleApplicationEntityToDomainMapper.initialize();


	@Override
	public BasePage<RoleApplication> findAll(final RoleApplicationListRequest listRequest) {

		final Pageable pageable = listRequest.getPageable().toPageable();

		Optional.ofNullable(listRequest.getFilter())
			.ifPresentOrElse(
				filter -> filter.setUserId(identity.getUserId()),
				() -> {
					RoleApplicationFilter filter = RoleApplicationFilter.builder()
						.userId(identity.getUserId())
						.build();

					listRequest.setFilter(filter);
				}
			);

		final Specification<RoleApplicationEntity> specification = Optional
			.ofNullable(listRequest.getFilter())
			.map(RoleApplicationFilter::toSpecification)
			.orElse(Specification.allOf());

		final Page<RoleApplicationEntity> roleApplicationEntitiesPage = roleApplicationRepository
			.findAll(specification, pageable);

		final List<RoleApplication> roleApplications = roleApplicationEntityToDomainMapper
			.map(roleApplicationEntitiesPage.getContent());

		return BasePage.of(
			listRequest.getFilter(),
			roleApplicationEntitiesPage,
			roleApplications
		);
	}

	@Override
	public void createAuthorApplication() {
		this.createApplication(RoleName.AUTHOR);
	}


	@Override
	public void createModeratorApplication() {
		this.createApplication(RoleName.MODERATOR);
	}


	private void createApplication(final RoleName roleName) {

		boolean existAnyApplicationInReview = roleApplicationRepository
			.existsByUserIdAndStatus(identity.getUserId(), RoleApplicationStatus.IN_REVIEW);
		if (existAnyApplicationInReview) {
			throw new RoleApplicationAlreadyExistException();
		}

		final RoleEntity role = roleRepository.findByName(roleName.name())
			.orElseThrow(() -> new RoleNotFoundByNameException(roleName.name()));

		final RoleApplicationEntity application = RoleApplicationEntity.builder()
			.user(UserEntity.builder().id(identity.getUserId()).build())
			.role(role)
			.status(RoleApplicationStatus.IN_REVIEW)
			.build();
		roleApplicationRepository.save(application);
	}


	@Override
	public void cancel() {

		final RoleApplicationEntity application = roleApplicationRepository
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
