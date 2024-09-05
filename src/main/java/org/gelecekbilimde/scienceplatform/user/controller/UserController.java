package org.gelecekbilimde.scienceplatform.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.user.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.user.model.response.UserRoleResponse;
import org.gelecekbilimde.scienceplatform.user.service.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final RoleService roleService;

	@PutMapping("/requestToBeAuthor")
	@PreAuthorize("hasAnyAuthority('landing:show','comment:create')") //todo rolün kendisi yazılacak
	public Response<UserRoleResponse> requestToBeAuthor() {
		return Response.ok(roleService.userRoletoAuthorRoleRequest());
	}

	@PutMapping("/requestToBeAdmin")
	@PreAuthorize("hasAuthority('admin:control')")
	public Response<UserRoleResponse> requestToBeAdmin(@RequestBody @Valid RoleChangeRequest request) {
		return Response.ok(roleService.makeUserToAdmin(request));
	}

	@PutMapping("/makeUserAuthor")
	@PreAuthorize("hasAuthority('admin:control')")
	public Response<UserRoleResponse> makeUserAuthor(@RequestBody @Valid RoleChangeRequest request) {
		return Response.ok(roleService.makeUserToAuthor(request));
	}

	@GetMapping("/getRequestToBeAuthor")
	@PreAuthorize("hasAuthority('admin:control')")
	public Response<List<UserRoleResponse>> getAllRequestToBeAuthor() {
		return Response.ok(roleService.getAllUserRoletoAuthorRoleRequest());
	}

	@DeleteMapping("/deleteRequestToBeAuthor")
	@PreAuthorize("hasAuthority('admin:control')")
	public Response deleteRequestToBeAuthor(@RequestBody @Valid RoleChangeRequest request) {
		return Response.ok(roleService.deleteUserRoletoAuthorRoleRequest(request));
	}
}
