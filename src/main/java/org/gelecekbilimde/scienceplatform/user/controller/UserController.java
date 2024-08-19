package org.gelecekbilimde.scienceplatform.user.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController {

	private final UserService userService;

	@PostMapping("{id}/follow/toggle")
	@PreAuthorize("isAuthenticated")
	public Response<Void> followToggle(@PathVariable String id) {
		this.userService.followToggle(id);
		return Response.NO_CONTENT;
	}

	@GetMapping("{id}/followers/")
	public Response<List<UserEntity>> getFollowers(@PathVariable String id) {
		List<UserEntity> userEntities = this.userService.getFollowers(id);
		return Response.ok(userEntities);
	}

	@GetMapping("{id}/followings/")
	public Response<List<UserEntity>> getFollowings(@PathVariable String id) {
		List<UserEntity> userEntities = this.userService.getFollowings(id);
		return Response.ok(userEntities);
	}
}
