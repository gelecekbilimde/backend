package org.gelecekbilimde.scienceplatform.auth.model.mapper;

import org.gelecekbilimde.scienceplatform.auth.model.entity.AuthorRequestEntity;
import org.gelecekbilimde.scienceplatform.auth.model.response.UserRoleResponse;
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
