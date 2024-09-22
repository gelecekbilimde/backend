package org.gelecekbilimde.scienceplatform.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.request.RoleChangeRequest;
import org.gelecekbilimde.scienceplatform.auth.model.UserRole;
import org.gelecekbilimde.scienceplatform.auth.service.RoleService;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PutMapping("/author-requests")
	@PreAuthorize("hasRole('USER')")
	public Response<Void> requestToBeAuthor() {
		roleService.userRoleToAuthorRoleRequest();
		return Response.NO_CONTENT;
	}

	@GetMapping("/author-requests")
	@PreAuthorize("hasRole('ADMIN')")
	public Response<List<UserRole>> getAllRequestToBeAuthor() {
		return Response.ok(roleService.getAllUserRoleToAuthorRoleRequest());
	}

	@DeleteMapping("/author-requests")
	@PreAuthorize("hasRole('ADMIN')")
	public Response<Void> deleteRequestToBeAuthor(@RequestBody @Valid RoleChangeRequest request) {
		roleService.deleteUserRoleToAuthorRoleRequest(request);
		return Response.NO_CONTENT;
	}

	@PutMapping("/moderator-requests")
	@PreAuthorize("hasRole('AUTHOR')")
	public Response<Void> requestToBeModerator() {
		roleService.authorRoleToModeratorRoleRequest();
		return Response.NO_CONTENT;
	}

	@PutMapping("/role-assigments")
	@PreAuthorize("hasRole('ADMIN')")
	public Response<Void> makeUserAuthor(@RequestBody @Valid RoleChangeRequest request) {
		roleService.changeUserRole(request);
		return Response.NO_CONTENT;
	}

}
