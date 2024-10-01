package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationDomainToRoleApplicationResponse;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequestsFilter;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleApplicationResponse;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoleApplicationController {

	private final RoleService roleService;
	private final RoleApplicationDomainToRoleApplicationResponse roleApplicationDomainToRoleApplicationResponse = RoleApplicationDomainToRoleApplicationResponse.initialize();

	@PostMapping("/role-applications")
	@PreAuthorize("hasAuthority('role:application:list')")
	public SuccessResponse<List<RoleApplicationResponse>> findAll(@RequestBody @Valid List<RoleChangeRequestsFilter> filters,
																  @RequestParam(value = "page", defaultValue = "0") int page,
																  @RequestParam(value = "size", defaultValue = "10") int size) {

		List<RoleApplicationResponse> roleApplicationResponses = roleApplicationDomainToRoleApplicationResponse
			.toRoleApplicationResponseList(roleService.getAllRoleChangeRequests(filters, page, size).stream().toList());
		return SuccessResponse.success(roleApplicationResponses);
	}

	@PostMapping("/role-application/author")
	@PreAuthorize("hasAuthority('role:application:create:author')")
	public SuccessResponse<Void> createAuthorApplication() {

		roleService.userRoleToAuthorRoleRequest();
		return SuccessResponse.success();
	}

	@PutMapping("/role-application/moderator")
	@PreAuthorize("hasAuthority('role:application:create:moderator')")
	public SuccessResponse<Void> createModeratorApplication() {

		roleService.authorRoleToModeratorRoleRequest();
		return SuccessResponse.success();
	}

	@PatchMapping("/role-application/{id}/approve")
	@PreAuthorize("hasAuthority('role:application:conclude')")
	public SuccessResponse<Void> approve(@PathVariable Long id) {

		roleService.approveRoleChangeRequest(id);
		return SuccessResponse.success();
	}

	@PatchMapping("/role-application/{id}/reject")
	@PreAuthorize("hasAuthority('role:application:conclude')")
	public SuccessResponse<Void> reject(@PathVariable Long id) {

		roleService.rejectRoleChangeRequest(id);
		return SuccessResponse.success();
	}

}
