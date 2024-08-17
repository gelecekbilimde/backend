package org.gelecekbilimde.scienceplatform.user.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
class UserController {

	private final UserService userService;

	@PostMapping("{id}/follow/toggle")
	public Response<Void> followToggle(@PathVariable String id) {
		this.userService.followToggle(id);
		return Response.NO_CONTENT;
	}
}
// /4/profile/follow/toggle
