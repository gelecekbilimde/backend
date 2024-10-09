package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserFollow;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowDeletePort;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowSavePort;
import org.gelecekbilimde.scienceplatform.user.port.UserReadPort;
import org.gelecekbilimde.scienceplatform.user.service.UserFollowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserFollowServiceImpl implements UserFollowService {

	private final UserFollowReadPort userFollowReadPort;
	private final UserFollowSavePort userFollowSavePort;
	private final UserFollowDeletePort userFollowDeletePort;
	private final UserReadPort userReadPort;
	private final Identity identity;


	@Override
	public List<User> findAllFollowings(final String id) {

		final User user = userReadPort.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		return userFollowReadPort.findAllByFollower(user)
			.stream()
			.map(UserFollow::getFollowed)
			.toList();
	}


	@Override
	public List<User> findAllFollowers(final String id) {

		final User user = userReadPort.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		return userFollowReadPort.findAllByFollowed(user)
			.stream()
			.map(UserFollow::getFollower)
			.toList();
	}


	@Override
	public void followToggle(final String id) {

		final User user = userReadPort.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		Optional<UserFollow> followerUserFromDatabase = userFollowReadPort
			.findByFollowedAndFollower(user, User.builder().id(identity.getUserId()).build());

		if (followerUserFromDatabase.isPresent()) {
			this.unfollowUser(followerUserFromDatabase.get());
			return;
		}

		this.followUser(user);
	}

	private void unfollowUser(UserFollow userFollow) {
		userFollowDeletePort.delete(userFollow);
	}

	private void followUser(User user) {
		UserFollow userFollow = UserFollow.builder()
			.follower(User.builder().id(identity.getUserId()).build())
			.followed(user)
			.build();
		userFollowSavePort.save(userFollow);
	}

}
