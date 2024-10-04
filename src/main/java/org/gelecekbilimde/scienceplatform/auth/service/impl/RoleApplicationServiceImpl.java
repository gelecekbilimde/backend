package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationAlreadyConcludedException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplicationFilter;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleApplicationListRequest;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleApplicationRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RoleApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class RoleApplicationServiceImpl implements RoleApplicationService {

	private final UserRepository userRepository;
	private final RoleApplicationRepository roleApplicationRepository;


	private final RoleApplicationEntityToDomainMapper roleApplicationEntityToDomainMapper = RoleApplicationEntityToDomainMapper.initialize();


	@Override
	public BasePage<RoleApplication> findAll(final RoleApplicationListRequest listRequest) {

		final Pageable pageable = listRequest.getPageable().toPageable();

		final RoleApplicationFilter filter = listRequest.getFilter();

		final Specification<RoleApplicationEntity> specification = Optional
			.ofNullable(filter)
			.map(RoleApplicationFilter::toSpecification)
			.orElse(Specification.allOf());

		final Page<RoleApplicationEntity> roleApplicationEntitiesPage = roleApplicationRepository
			.findAll(specification, pageable);

		final List<RoleApplication> roleApplications = roleApplicationEntityToDomainMapper
			.map(roleApplicationEntitiesPage.getContent());

		return BasePage.of(
			filter,
			roleApplicationEntitiesPage,
			roleApplications
		);
	}

	@Override
	@Transactional
	public void approve(final String id) {

		RoleApplicationEntity application = roleApplicationRepository.findById(id)
			.orElseThrow(() -> new RoleApplicationNotFoundByIdException(id));

		if (application.isConcluded()) {
			throw new RoleApplicationAlreadyConcludedException(id);
		}

		UserEntity user = application.getUser();
		user.setRoleId(application.getRole().getId());
		user.setRoleEntity(application.getRole());
		userRepository.save(user);

		application.approve();
		roleApplicationRepository.save(application);
	}


	@Override
	public void reject(final String id) {

		RoleApplicationEntity application = roleApplicationRepository.findById(id)
			.orElseThrow(() -> new RoleApplicationNotFoundByIdException(id));

		if (application.isConcluded()) {
			throw new RoleApplicationAlreadyConcludedException(id);
		}

		application.reject();
		roleApplicationRepository.save(application);
	}

}
