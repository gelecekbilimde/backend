package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleChangeInAssesmentException;
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
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final Identity identity;
	private final UserRepository userRepository;
	private final RoleChangeRepository roleChangeRepository;
	private final RoleRepository roleRepository;
	private final AuthorRequestEntityToUserRoleResponseMapper authorRequestEntityToUserRoleResponseMapper = AuthorRequestEntityToUserRoleResponseMapper.initialize();

	@Override
	public void userRoleToAuthorRoleRequest() {
		hasInAssesmentRequest();
		RoleEntity role = roleRepository.findByName(RoleName.AUTHOR.name())
			.orElseThrow(RoleNotFoundException::new);
		RoleApplicationEntity authorRequest = RoleApplicationEntity.builder()
			.user(getUserById())
			.role(role)
			.status(RoleChangeStatus.IN_ASSESSMENT)
			.createdAt(LocalDateTime.now())
			.createdBy(getUserById().getId())
			.build();
		roleChangeRepository.save(authorRequest);
	}

	@Override
	public void authorRoleToModeratorRoleRequest() {
		hasInAssesmentRequest();
		RoleEntity role = roleRepository.findByName(RoleName.MODERATOR.name())
			.orElseThrow(RoleNotFoundException::new);
		RoleApplicationEntity authorRequest = RoleApplicationEntity.builder()
			.user(getUserById())
			.role(role)
			.status(RoleChangeStatus.IN_ASSESSMENT)
			.build();
		roleChangeRepository.save(authorRequest);
	}

	@Override
	@Transactional
	public void approveRoleChangeRequest(Long requestId) {
		RoleApplicationEntity roleChange = roleChangeRepository.findById(requestId)
			.orElseThrow(() -> new RoleChangeNotFoundByIdException(requestId));
		UserEntity user = roleChange.getUser();
		user.setRoleId(roleChange.getRole().getId());
		user.setRoleEntity(roleChange.getUser().getRoleEntity());
		userRepository.save(user);
		roleChange.setStatus(RoleChangeStatus.CONFIRMED);
		roleChangeRepository.save(roleChange);
	}

	@Override
	public void rejectRoleChangeRequest(Long requestId) {
		RoleApplicationEntity roleChange = roleChangeRepository.findById(requestId)
			.orElseThrow(() -> new RoleChangeNotFoundByIdException(requestId));
		roleChange.setStatus(RoleChangeStatus.REJECTED);
		roleChangeRepository.save(roleChange);
	}

	@Override
	public void moderatorAssignment(String id) {
		RoleEntity role = roleRepository.findByName(RoleName.MODERATOR.name())
			.orElseThrow(RoleNotFoundException::new);
		UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
		user.setRoleId(role.getId());
		user.setRoleEntity(role);
		userRepository.save(user);
	}

	@Override
	public Page<RoleApplication> getAllRoleChangeRequests(List<RoleChangeRequestsFilter> filters, int page, int size) {
		Pageable pageable = PageRequest.of(page,size,Sort.by("createdAt").descending());
		Page<RoleApplicationEntity> authorRequests = roleChangeRepository.findAll(RoleChangeSpecification.columnEqual(filters),pageable);
		List<RoleApplication> roleApplications = authorRequests.stream().map(authorRequestEntityToUserRoleResponseMapper::map).toList();
		return new PageImpl<>(roleApplications, pageable, authorRequests.getTotalElements());
	}

	private UserEntity getUserById() {
		return userRepository.findById(identity.getUserId())
			.orElseThrow(() -> new UserNotFoundByIdException(identity.getUserId()));
	}

	private void hasInAssesmentRequest() {
		boolean hasInAssesmentRequest = roleChangeRepository.existsByUserAndStatus(getUserById(), RoleChangeStatus.IN_ASSESSMENT);
		if (hasInAssesmentRequest) {
			throw new RoleChangeInAssesmentException();
		}
	}

}
