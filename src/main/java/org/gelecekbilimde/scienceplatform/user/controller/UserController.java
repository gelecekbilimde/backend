package org.gelecekbilimde.scienceplatform.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserToUserFollowMapper;
import org.gelecekbilimde.scienceplatform.user.model.request.RemoveFollower;
import org.gelecekbilimde.scienceplatform.user.model.response.UserFollow;
import org.gelecekbilimde.scienceplatform.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {

	private final UserService userService;
	private final UserToUserFollowMapper userToUserFollowMapper = UserToUserFollowMapper.initialize();

	@PostMapping("/{id}/follow/toggle")
	@PreAuthorize("isAuthenticated()")
	public Response<Void> followToggle(@PathVariable String id) {
		this.userService.followToggle(id);
		return Response.NO_CONTENT;
	}

	@PostMapping("/followers/remove")
	public Response<Void> removeFollowers(@RequestBody @Valid RemoveFollower request) {
		this.userService.removeFollower(request);
		return Response.NO_CONTENT;
	}

	@GetMapping("/{id}/followers")
	public Response<List<UserFollow>> getFollowers(@PathVariable String id) {
		List<User> users = this.userService.getFollowers(id);
		return Response.ok(userToUserFollowMapper.map(users));
	}

	@GetMapping("/{id}/followings")
	public Response<List<UserFollow>> getFollowings(@PathVariable String id) {
		List<User> users = this.userService.getFollowings(id);
		return Response.ok(userToUserFollowMapper.map(users));
	}
}
