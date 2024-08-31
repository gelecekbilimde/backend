package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.gelecekbilimde.scienceplatform.auth.repository.RoleRepository;
import org.gelecekbilimde.scienceplatform.common.exception.ClientException;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.user.model.entity.AuthorRequestEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.request.UserToAuthorRequest;
import org.gelecekbilimde.scienceplatform.user.model.response.UserRoleResponse;
import org.gelecekbilimde.scienceplatform.user.repository.AuthorRequestRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.user.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final Identity identity;
	private final UserRepository userRepository;
	private final AuthorRequestRepository authorRequestRepository;
	private final RoleRepository roleRepository;

	@Override
	public UserRoleResponse userRoletoAuthorRoleRequest() {
		//todo exception düzenlemesi yapılacak
		UserEntity user = userRepository.findById(identity.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found."));
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
	public UserRoleResponse makeUserToAuthor(UserToAuthorRequest request) {
		//todo exception düzenlemesi yapılacak
		UserEntity user = userRepository.findById(identity.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found."));
		RoleEntity roleEntity = roleRepository.findByName("AUTHOR").orElseThrow(()-> new ClientException("Role not found."));

		if (user.getRoleEntity().getName().equals("USER")){
			user.setRoleId(roleEntity.getId());
			user.setRoleEntity(roleEntity);
		}else {
			throw new ClientException("user's role is not user");
		}
		return UserRoleResponse.builder()
			.userEmail(user.getEmail())
			.userId(user.getId())
			.roleName(user.getRoleEntity().getName())
			.build();
	}
}
