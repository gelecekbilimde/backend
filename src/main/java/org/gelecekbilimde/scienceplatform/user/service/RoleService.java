package org.gelecekbilimde.scienceplatform.user.service;

import org.gelecekbilimde.scienceplatform.user.model.request.UserToAuthorRequest;
import org.gelecekbilimde.scienceplatform.user.model.response.UserRoleResponse;

public interface RoleService {
	UserRoleResponse userRoletoAuthorRoleRequest();

	UserRoleResponse makeUserToAuthor(UserToAuthorRequest request);
}
