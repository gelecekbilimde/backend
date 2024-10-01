package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationDomainToRoleApplicationResponse;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequestsFilter;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleApplicationResponse;
import org.gelecekbilimde.scienceplatform.auth.service.RoleApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class RoleApplicationController {

	private final RoleApplicationService roleApplicationService;


	private final RoleApplicationDomainToRoleApplicationResponse roleApplicationDomainToRoleApplicationResponse = RoleApplicationDomainToRoleApplicationResponse.initialize();


	@PostMapping("/role-applications")
	@PreAuthorize("hasAnyAuthority('role:application:list')")
	SuccessResponse<List<RoleApplicationResponse>> findAll(@RequestBody @Valid List<RoleChangeRequestsFilter> filters,
														   @RequestParam(value = "page", defaultValue = "0") int page,
														   @RequestParam(value = "size", defaultValue = "10") int size) {

		List<RoleApplicationResponse> roleApplicationResponses = roleApplicationDomainToRoleApplicationResponse
			.toRoleApplicationResponseList(roleApplicationService.findAll(filters, page, size).stream().toList());
		return SuccessResponse.success(roleApplicationResponses);
	}

	@PostMapping("/role-application/author")
	@PreAuthorize("hasAnyAuthority('role:application:create:author')")
	SuccessResponse<Void> createAuthorApplication() {

		roleApplicationService.createAuthorApplication();
		return SuccessResponse.success();
	}

	@PutMapping("/role-application/moderator")
	@PreAuthorize("hasAnyAuthority('role:application:create:moderator')")
	SuccessResponse<Void> createModeratorApplication() {

		roleApplicationService.createModeratorApplication();
		return SuccessResponse.success();
	}

	@PatchMapping("/role-application/{id}/approve")
	@PreAuthorize("hasAnyAuthority('role:application:conclude')")
	SuccessResponse<Void> approve(@PathVariable @Positive Long id) {

		roleApplicationService.approve(id);
		return SuccessResponse.success();
	}

	@PatchMapping("/role-application/{id}/reject")
	@PreAuthorize("hasAnyAuthority('role:application:conclude')")
	SuccessResponse<Void> reject(@PathVariable @Positive Long id) {

		roleApplicationService.reject(id);
		return SuccessResponse.success();
	}

}
