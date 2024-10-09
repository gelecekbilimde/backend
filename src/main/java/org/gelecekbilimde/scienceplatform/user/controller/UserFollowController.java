package org.gelecekbilimde.scienceplatform.user.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserToUserFollowResponseMapper;
import org.gelecekbilimde.scienceplatform.user.model.response.UserFollowResponse;
import org.gelecekbilimde.scienceplatform.user.service.UserFollowService;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class UserFollowController {

	private final UserFollowService userFollowService;
	private final UserToUserFollowResponseMapper userToUserFollowResponseMapper = UserToUserFollowResponseMapper.initialize();


	@GetMapping("/user/{id}/followings")
	SuccessResponse<List<UserFollowResponse>> findAllFollowings(@PathVariable @UUID String id) {
		List<User> users = this.userFollowService.findAllFollowings(id);
		List<UserFollowResponse> userFollowResponses = userToUserFollowResponseMapper.map(users);
		return SuccessResponse.success(userFollowResponses);
	}


	@GetMapping("/user/{id}/followers")
	SuccessResponse<List<UserFollowResponse>> findAllFollowers(@PathVariable @UUID String id) {
		List<User> users = this.userFollowService.findAllFollowers(id);
		List<UserFollowResponse> userFollowResponses = userToUserFollowResponseMapper.map(users);
		return SuccessResponse.success(userFollowResponses);
	}


	@PostMapping("/user/{id}/follow/toggle")
	@PreAuthorize("hasAnyAuthority('user:follow', 'user:unfollow')")
	SuccessResponse<Void> followToggle(@PathVariable @UUID String id) {
		this.userFollowService.followToggle(id);
		return SuccessResponse.success();
	}

}
