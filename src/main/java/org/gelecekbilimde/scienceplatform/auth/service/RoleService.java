package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.model.UserRole;

import java.util.List;

public interface RoleService {

	void userRoleToAuthorRoleRequest();

	List<UserRole> getAllUserRoleToAuthorRoleRequest();

	void deleteUserRoleToAuthorRoleRequest(RoleChangeRequest request);

	void changeUserRole(RoleChangeRequest request);

	void authorRoleToModeratorRoleRequest();

}
