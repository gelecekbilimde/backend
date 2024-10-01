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
@RequestMapping("/api/v1/role-application")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
	private final RoleApplicationDomainToRoleApplicationResponse roleApplicationDomainToRoleApplicationResponse = RoleApplicationDomainToRoleApplicationResponse.initialize();

    @PostMapping("/author")
    @PreAuthorize("hasAuthority('role:request:author')")
    public SuccessResponse<Void> requestToBeAuthor() {
        roleService.userRoleToAuthorRoleRequest();
        return SuccessResponse.success();
    }

    @PostMapping("/requests")
    @PreAuthorize("hasAuthority('role:change:authority')")
    public SuccessResponse<List<RoleApplicationResponse>> getAllRoleChangeRequests(
		@RequestBody @Valid List<RoleChangeRequestsFilter> filters,
		@RequestParam(value = "page",defaultValue = "0") int page,
		@RequestParam(value = "size",defaultValue = "10") int size
	) {
		List<RoleApplicationResponse> roleApplicationResponses = roleApplicationDomainToRoleApplicationResponse
			.toRoleApplicationResponseList(roleService.getAllRoleChangeRequests(filters,page,size).stream().toList());
        return SuccessResponse.success(roleApplicationResponses);
    }

    @PutMapping("/moderator")
    @PreAuthorize("hasAuthority('role:request:moderator')")
    public SuccessResponse<Void> requestToBeModerator() {
        roleService.authorRoleToModeratorRoleRequest();
        return SuccessResponse.success();
    }

	@PatchMapping("/{id}/approve")
	@PreAuthorize("hasAuthority('role:change:authority')")
	public SuccessResponse<Void> approveRoleRequest(@PathVariable Long id) {
		roleService.approveRoleChangeRequest(id);
		return SuccessResponse.success();
	}

	@PatchMapping("/{id}/reject")
	@PreAuthorize("hasAuthority('role:change:authority')")
	public SuccessResponse<Void> rejectRoleRequest(@PathVariable Long id) {
		roleService.rejectRoleChangeRequest(id);
		return SuccessResponse.success();
	}

	@PatchMapping("/{id}/moderator")
	@PreAuthorize("hasAuthority('role:change:authority')")
	public SuccessResponse<Void> moderatorAssignment(@PathVariable String id){
		roleService.moderatorAssignment(id);
		return SuccessResponse.success();
	}

}
