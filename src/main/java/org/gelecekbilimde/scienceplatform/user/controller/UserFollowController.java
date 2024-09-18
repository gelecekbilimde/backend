package org.gelecekbilimde.scienceplatform.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserToUserFollowResponseMapper;
import org.gelecekbilimde.scienceplatform.user.model.request.UnfollowRequest;
import org.gelecekbilimde.scienceplatform.user.model.response.UserFollowResponse;
import org.gelecekbilimde.scienceplatform.user.service.UserFollowService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserFollowController {

	private final UserFollowService userFollowService;
	private final UserToUserFollowResponseMapper userToUserFollowResponseMapper = UserToUserFollowResponseMapper.initialize();

	@PostMapping("/{id}/follow/toggle")
	SuccessResponse<Void> followToggle(@PathVariable @UUID String id) {
		this.userFollowService.followToggle(id);
		return SuccessResponse.success();
	}

	@PostMapping("/followers/remove")
	SuccessResponse<Void> removeFollowers(@RequestBody @Valid UnfollowRequest request) {
		this.userFollowService.removeFollower(request);
		return SuccessResponse.success();
	}

	@GetMapping("/{id}/followers")
	SuccessResponse<List<UserFollowResponse>> findAllFollowers(@PathVariable @UUID String id) {
		List<User> users = this.userFollowService.findAllFollowers(id);
		List<UserFollowResponse> userFollowResponses = userToUserFollowResponseMapper.map(users);
		return SuccessResponse.success(userFollowResponses);
	}

	@GetMapping("/{id}/followings")
	SuccessResponse<List<UserFollowResponse>> findAllFollowings(@PathVariable @UUID String id) {
		List<User> users = this.userFollowService.findAllFollowings(id);
		List<UserFollowResponse> userFollowResponses = userToUserFollowResponseMapper.map(users);
		return SuccessResponse.success(userFollowResponses);
	}

}
