package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.RoleNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.auth.exception.AdminRoleConflictException;
import org.gelecekbilimde.scienceplatform.auth.exception.AuthorRequestNotFound;
import org.gelecekbilimde.scienceplatform.auth.exception.UserRoleConflictException;
import org.gelecekbilimde.scienceplatform.auth.model.entity.AuthorRequestEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.AuthorRequestEntityToUserRoleResponse;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.UserRoleResponse;
import org.gelecekbilimde.scienceplatform.auth.repository.AuthorRequestRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
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
	private final AuthorRequestEntityToUserRoleResponse authorRequestEntityToUserRoleResponse;

	@Override
	public UserRoleResponse userRoletoAuthorRoleRequest() {
		UserEntity user = userRepository.findById(identity.getUserId()).orElseThrow(UserNotFoundException::new); //todo principial kullanılabilir identity yerine
		AuthorRequestEntity authorRequest = AuthorRequestEntity.builder()
			.id(RandomUtil.generateUUID())
			.user(user)
			.build();
		authorRequestRepository.save(authorRequest);

		return UserRoleResponse.builder()
			.userId(user.getId())
			.roleName(user.getRoleEntity().getName())
			.userEmail(user.getEmail())
			.build();
	}

	@Override
	@Transactional
	public UserRoleResponse makeUserToAuthor(RoleChangeRequest request) {
		UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
		RoleEntity roleEntity = roleRepository.findByName("AUTHOR").orElseThrow(RoleNotFoundException::new);
		AuthorRequestEntity authorRequest = authorRequestRepository.findByUserId(user.getId()).orElseThrow(AuthorRequestNotFound::new);
		if (user.getRoleEntity().getName().equals("USER")){
			user.setRoleId(roleEntity.getId());
			user.setRoleEntity(roleEntity);
			userRepository.save(user);
			authorRequestRepository.delete(authorRequest);
		}else {
			throw new UserRoleConflictException();
		}
		return UserRoleResponse.builder()
			.userEmail(user.getEmail())
			.userId(user.getId())
			.roleName(user.getRoleEntity().getName())
			.build();
	}

	@Override
	public List<UserRoleResponse> getAllUserRoletoAuthorRoleRequest() {
		List<AuthorRequestEntity> authorRequests = authorRequestRepository.findAll();
		return authorRequests.stream().map(authorRequestEntityToUserRoleResponse::map).toList();
	}

	@Override
	public Response deleteUserRoletoAuthorRoleRequest(RoleChangeRequest request) {
		AuthorRequestEntity authorRequest = authorRequestRepository.findByUserId(request.getUserId()).orElseThrow(AuthorRequestNotFound::new);
		authorRequestRepository.delete(authorRequest);
		return Response.NO_CONTENT;
	}

	@Override
	public UserRoleResponse makeUserToAdmin(RoleChangeRequest request) {
		UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(UserNotFoundException::new);
		RoleEntity roleEntity = roleRepository.findByName("ADMIN").orElseThrow(RoleNotFoundException::new);
		if (user.getRoleId().equals(roleEntity.getId())){
			throw new AdminRoleConflictException();
		}
		user.setRoleId(roleEntity.getId());
		user.setRoleEntity(roleEntity);
		userRepository.save(user);
		return UserRoleResponse.builder()
			.roleName(user.getRoleEntity().getName())
			.userId(user.getId())
			.userEmail(user.getEmail())
			.build();
	}
}
