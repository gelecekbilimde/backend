package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.UserRoleResponse;

import java.util.List;

public interface RoleService {
	UserRoleResponse userRoletoAuthorRoleRequest();

	UserRoleResponse makeUserToAuthor(RoleChangeRequest request);

	List<UserRoleResponse> getAllUserRoletoAuthorRoleRequest();

	Response deleteUserRoletoAuthorRoleRequest(RoleChangeRequest request);

	UserRoleResponse makeUserToAdmin(RoleChangeRequest request);
}
