package org.gelecekbilimde.scienceplatform.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserToUserFollowResponseMapper;
import org.gelecekbilimde.scienceplatform.user.model.request.FollowRemoveRequest;
import org.gelecekbilimde.scienceplatform.user.model.response.UserFollowResponse;
import org.gelecekbilimde.scienceplatform.user.service.UserFollowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController {

	private final UserFollowService userFollowService;
	private final UserToUserFollowResponseMapper userToUserFollowResponseMapper = UserToUserFollowResponseMapper.initialize();

	@PostMapping("/{id}/follow/toggle")
	public Response<Void> followToggle(@PathVariable String id) {
		this.userFollowService.followToggle(id);
		return Response.NO_CONTENT;
	}

	@PostMapping("/followers/remove")
	public Response<Void> removeFollowers(@RequestBody @Valid FollowRemoveRequest request) {
		this.userFollowService.removeFollower(request);
		return Response.NO_CONTENT;
	}

	@GetMapping("/{id}/followers")
	public Response<List<UserFollowResponse>> findAllFollowers(@PathVariable String id) {
		List<User> users = this.userFollowService.findAllFollowers(id);
		return Response.ok(userToUserFollowResponseMapper.map(users));
	}

	@GetMapping("/{id}/followings")
	public Response<List<UserFollowResponse>> findAllFollowings(@PathVariable String id) {
		List<User> users = this.userFollowService.findAllFollowings(id);
		return Response.ok(userToUserFollowResponseMapper.map(users));
	}
}
