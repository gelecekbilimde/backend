package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleApplicationAlreadyExistException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleChangeNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.RoleChangeSpecification;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleChangeStatus;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleName;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.AuthorRequestEntityToUserRoleResponseMapper;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequestsFilter;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleChangeRepository;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RoleApplicationService;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class RoleApplicationServiceImpl implements RoleApplicationService {

	private final UserRepository userRepository;
	private final RoleChangeRepository roleChangeRepository;
	private final RoleRepository roleRepository;
	private final Identity identity;


	private final AuthorRequestEntityToUserRoleResponseMapper authorRequestEntityToUserRoleResponseMapper = AuthorRequestEntityToUserRoleResponseMapper.initialize();


	@Override
	public Page<RoleApplication> findAll(List<RoleChangeRequestsFilter> filters, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		Page<RoleApplicationEntity> authorRequests = roleChangeRepository.findAll(RoleChangeSpecification.columnEqual(filters), pageable);
		List<RoleApplication> roleApplications = authorRequests.stream().map(authorRequestEntityToUserRoleResponseMapper::map).toList();
		return new PageImpl<>(roleApplications, pageable, authorRequests.getTotalElements());
	}


	@Override
	public void createAuthorApplication() {

		UserEntity user = userRepository.findById(identity.getUserId())
			.orElseThrow(() -> new UserNotFoundByIdException(identity.getUserId()));

		boolean existAnyApplicationInReview = roleChangeRepository
			.existsByUserAndStatus(user, RoleChangeStatus.IN_REVIEW);
		if (existAnyApplicationInReview) {
			throw new RoleApplicationAlreadyExistException();
		}

		RoleEntity role = roleRepository.findByName(RoleName.AUTHOR.name())
			.orElseThrow(RoleNotFoundException::new);

		RoleApplicationEntity application = RoleApplicationEntity.builder()
			.user(user)
			.role(role)
			.status(RoleChangeStatus.IN_REVIEW)
			.build();
		roleChangeRepository.save(application);
	}


	@Override
	public void createModeratorApplication() {

		UserEntity user = userRepository.findById(identity.getUserId())
			.orElseThrow(() -> new UserNotFoundByIdException(identity.getUserId()));

		boolean existAnyApplicationInReview = roleChangeRepository
			.existsByUserAndStatus(user, RoleChangeStatus.IN_REVIEW);
		if (existAnyApplicationInReview) {
			throw new RoleApplicationAlreadyExistException();
		}

		RoleEntity role = roleRepository.findByName(RoleName.MODERATOR.name())
			.orElseThrow(RoleNotFoundException::new);

		RoleApplicationEntity application = RoleApplicationEntity.builder()
			.user(user)
			.role(role)
			.status(RoleChangeStatus.IN_REVIEW)
			.build();
		roleChangeRepository.save(application);
	}


	@Override
	@Transactional
	public void approve(final Long id) {

		RoleApplicationEntity application = roleChangeRepository.findById(id)
			.orElseThrow(() -> new RoleChangeNotFoundByIdException(id));

		// TODO : Check if the user has a role change request in progress

		UserEntity user = application.getUser();
		user.setRoleId(application.getRole().getId());
		user.setRoleEntity(application.getUser().getRoleEntity());
		userRepository.save(user);

		application.approve();
		roleChangeRepository.save(application);
	}


	@Override
	public void reject(final Long id) {

		RoleApplicationEntity application = roleChangeRepository.findById(id)
			.orElseThrow(() -> new RoleChangeNotFoundByIdException(id));

		// TODO : Check if the user has a role change request in progress

		application.reject();
		roleChangeRepository.save(application);
	}

}
