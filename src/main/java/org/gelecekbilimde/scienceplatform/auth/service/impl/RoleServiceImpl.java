package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.AuthorRequestNotFound;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.UserRole;
import org.gelecekbilimde.scienceplatform.auth.model.entity.AuthorRequestEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.Role;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.AuthorRequestEntityToUserRoleResponseMapper;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.repository.AuthorRequestRepository;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final Identity identity;
	private final UserRepository userRepository;
	private final AuthorRequestRepository authorRequestRepository;
	private final RoleRepository roleRepository;
	private final AuthorRequestEntityToUserRoleResponseMapper authorRequestEntityToUserRoleResponseMapper = AuthorRequestEntityToUserRoleResponseMapper.initialize();

	@Override
	public void userRoleToAuthorRoleRequest() {
		UserEntity user = userRepository.findById(identity.getUserId())
			.orElseThrow(UserNotFoundException::new);
		AuthorRequestEntity authorRequest = AuthorRequestEntity.builder()
			.user(user)
			.requestRoleName(Role.AUTHOR)
			.build();
		authorRequestRepository.save(authorRequest);
	}

	@Override
	@Transactional
	public void changeUserRole(RoleChangeRequest request) {
		UserEntity user = userRepository.findById(request.getUserId())
			.orElseThrow(UserNotFoundException::new);
		RoleEntity roleEntity = roleRepository.findByName(request.getRoleName())
			.orElseThrow(RoleNotFoundException::new);
		AuthorRequestEntity authorRequest = authorRequestRepository.findByUserId(user.getId())
			.orElseThrow(AuthorRequestNotFound::new);
		user.setRoleId(roleEntity.getId());
		user.setRoleEntity(roleEntity);
		userRepository.save(user);
		authorRequestRepository.delete(authorRequest);
	}

	@Override
	public void authorRoleToModeratorRoleRequest() {
		UserEntity user = userRepository.findById(identity.getUserId())
			.orElseThrow(UserNotFoundException::new);
		AuthorRequestEntity authorRequest = AuthorRequestEntity.builder()
			.user(user)
			.requestRoleName(Role.MODERATOR)
			.build();
		authorRequestRepository.save(authorRequest);
	}

	@Override
	public List<UserRole> getAllUserRoleToAuthorRoleRequest() {
		List<AuthorRequestEntity> authorRequests = authorRequestRepository.findAll();
		return authorRequests.stream().map(authorRequestEntityToUserRoleResponseMapper::map).toList();
	}

	@Override
	public void deleteUserRoleToAuthorRoleRequest(RoleChangeRequest request) {
		AuthorRequestEntity authorRequest = authorRequestRepository.findByUserId(request.getUserId())
			.orElseThrow(AuthorRequestNotFound::new);
		authorRequestRepository.delete(authorRequest);
	}

}
