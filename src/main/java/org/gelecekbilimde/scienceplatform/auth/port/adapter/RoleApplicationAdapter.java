package org.gelecekbilimde.scienceplatform.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplicationFilter;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationToEntityMapper;
import org.gelecekbilimde.scienceplatform.auth.port.RoleApplicationReadPort;
import org.gelecekbilimde.scienceplatform.auth.port.RoleApplicationSavePort;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleApplicationRepository;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class RoleApplicationAdapter implements RoleApplicationReadPort, RoleApplicationSavePort {

	private final RoleApplicationRepository roleApplicationRepository;


	private final RoleApplicationEntityToDomainMapper roleApplicationEntityToDomainMapper = RoleApplicationEntityToDomainMapper.initialize();
	private final RoleApplicationToEntityMapper roleApplicationToEntityMapper = RoleApplicationToEntityMapper.initialize();


	@Override
	public BasePage<RoleApplication> findAll(final BasePageable basePageable,
											 final RoleApplicationFilter filter) {

		final Pageable pageable = basePageable.toPageable();

		final Specification<RoleApplicationEntity> specification = filter.toSpecification();

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
	public Optional<RoleApplication> findById(final String id) {
		return roleApplicationRepository.findById(id)
			.map(roleApplicationEntityToDomainMapper::map);
	}


	@Override
	public Optional<RoleApplication> findByUserIdAndStatus(final String userId,
														   final RoleApplicationStatus status) {

		return roleApplicationRepository.findByUserIdAndStatus(userId, status)
			.map(roleApplicationEntityToDomainMapper::map);
	}


	@Override
	public boolean existsByUserIdAndStatus(final String userId,
										   final RoleApplicationStatus status) {

		return roleApplicationRepository.existsByUserIdAndStatus(userId, status);
	}


	@Override
	public RoleApplication save(final RoleApplication roleApplication) {
		final RoleApplicationEntity roleApplicationEntity = roleApplicationToEntityMapper.map(roleApplication);
		final RoleApplicationEntity savedRoleApplicationEntity = roleApplicationRepository.save(roleApplicationEntity);
		return roleApplicationEntityToDomainMapper.map(savedRoleApplicationEntity);
	}

}
