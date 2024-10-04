package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.RoleApplication;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.RoleApplicationToRoleApplicationResponseMapper;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleSelfApplicationListRequest;
import org.gelecekbilimde.scienceplatform.auth.model.response.RoleApplicationsResponse;
import org.gelecekbilimde.scienceplatform.auth.service.RoleSelfApplicationService;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class RoleSelfApplicationController {

	private final RoleSelfApplicationService roleSelfApplicationService;


	private final RoleApplicationToRoleApplicationResponseMapper roleApplicationToRoleApplicationResponseMapper = RoleApplicationToRoleApplicationResponseMapper.initialize();


	@PostMapping("/role-applications/self")
	@PreAuthorize("hasAnyAuthority('role:application:list:self')")
	SuccessResponse<PagingResponse<RoleApplicationsResponse>> findAll(@RequestBody @Valid RoleSelfApplicationListRequest listRequest) {

		final BasePage<RoleApplication> pageOfRoleApplications = roleSelfApplicationService.findAll(listRequest);

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


	@PostMapping("/role-application/self/author")
	@PreAuthorize("hasAnyAuthority('role:application:create:self:author')")
	SuccessResponse<Void> createAuthorApplication() {

		roleSelfApplicationService.createAuthorApplication();
		return SuccessResponse.success();
	}


	@PostMapping("/role-application/self/moderator")
	@PreAuthorize("hasAnyAuthority('role:application:create:self:moderator')")
	SuccessResponse<Void> createModeratorApplication() {

		roleSelfApplicationService.createModeratorApplication();
		return SuccessResponse.success();
	}


	@PatchMapping("/role-application/self/cancel")
	@PreAuthorize("hasAnyAuthority('role:application:cancel:self')")
	SuccessResponse<Void> cancel() {
		roleSelfApplicationService.cancel();
		return SuccessResponse.success();
	}

}
