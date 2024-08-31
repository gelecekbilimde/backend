package org.gelecekbilimde.scienceplatform.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.user.model.request.UserToAuthorRequest;
import org.gelecekbilimde.scienceplatform.user.model.response.UserRoleResponse;
import org.gelecekbilimde.scienceplatform.user.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final RoleService roleService;

	@PutMapping("/requestToBeAuthor")
	@PreAuthorize("hasAnyAuthority('landing:show','comment:create')")
	public Response<UserRoleResponse> requestToBeAuthor() {
		return Response.ok(roleService.userRoletoAuthorRoleRequest());
	}

	@PutMapping("/makeUserAuthor")
	@PreAuthorize("hasAuthority('admin:control')")
	public Response<UserRoleResponse> makeUserAuthor(@RequestBody @Valid UserToAuthorRequest request){
		return Response.ok(roleService.makeUserToAuthor(request));
	}
}
