package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationToRoleApplicationResponseMapper;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleApplicationListRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleApplicationsResponse;
import org.gelecekbilimde.scienceplatform.auth.service.RoleApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class RoleApplicationController {

	private final RoleApplicationService roleApplicationService;


	private final RoleApplicationToRoleApplicationResponseMapper roleApplicationToRoleApplicationResponseMapper = RoleApplicationToRoleApplicationResponseMapper.initialize();


	@PostMapping("/role-applications")
	@PreAuthorize("hasAnyAuthority('role:application:list')")
	SuccessResponse<PagingResponse<RoleApplicationsResponse>> findAll(@RequestBody @Valid RoleApplicationListRequest listRequest) {

		final BasePage<RoleApplication> pageOfRoleApplications = roleApplicationService.findAll(listRequest);

		final PagingResponse<RoleApplicationsResponse> pageResponseOfRoleApplication = PagingResponse
			.<RoleApplicationsResponse>builder()
			.of(pageOfRoleApplications)
			.content(
				roleApplicationToRoleApplicationResponseMapper.map(pageOfRoleApplications.getContent())
			)
			.filteredBy(listRequest.getFilter())
			.build();

		return SuccessResponse.success(pageResponseOfRoleApplication);
	}

	@PatchMapping("/role-application/{id}/approve")
	@PreAuthorize("hasAnyAuthority('role:application:conclude')")
	SuccessResponse<Void> approve(@PathVariable @UUID String id) {

		roleApplicationService.approve(id);
		return SuccessResponse.success();
	}

	@PatchMapping("/role-application/{id}/reject")
	@PreAuthorize("hasAnyAuthority('role:application:conclude')")
	SuccessResponse<Void> reject(@PathVariable @UUID String id) {

		roleApplicationService.reject(id);
		return SuccessResponse.success();
	}

}
