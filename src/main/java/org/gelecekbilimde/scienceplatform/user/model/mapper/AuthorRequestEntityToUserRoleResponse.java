package org.gelecekbilimde.scienceplatform.user.model.mapper;

import org.gelecekbilimde.scienceplatform.user.model.entity.AuthorRequestEntity;
import org.gelecekbilimde.scienceplatform.user.model.response.UserRoleResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthorRequestEntityToUserRoleResponse {
	public UserRoleResponse map(AuthorRequestEntity authorRequest){
		return UserRoleResponse.builder()
			.userId(authorRequest.getUser().getId())
			.roleName(authorRequest.getUser().getRoleEntity().getName())
			.userEmail(authorRequest.getUser().getEmail())
			.build();
	}
}
