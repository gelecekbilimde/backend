package org.gelecekbilimde.scienceplatform.auth.service;

import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequestsFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleApplicationService {

	void userRoleToAuthorRoleRequest();

	Page<RoleApplication> getAllRoleChangeRequests(List<RoleChangeRequestsFilter> filters, int page, int size);

	void authorRoleToModeratorRoleRequest();

	void approveRoleChangeRequest(Long requestId);

	void rejectRoleChangeRequest(Long requestId);

	void moderatorAssignment(String id);

}
