package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.user.exception.UserAlreadyUnfollowedException;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.gelecekbilimde.scienceplatform.user.model.request.UnfollowRequest;
import org.gelecekbilimde.scienceplatform.user.port.UserReadPort;
import org.gelecekbilimde.scienceplatform.user.repository.UserFollowRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserFollowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class UserFollowServiceImpl implements UserFollowService {

	private final UserFollowRepository userFollowRepository;
	private final UserReadPort userReadPort;
	private final Identity identity;


	@Override
	public List<User> findAllFollowings(String id) {
		final User user = userReadPort.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));
		return user.getFollowings();
	}


	@Override
	public List<User> findAllFollowers(String id) {
		final User user = userReadPort.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));
		return user.getFollowers();
	}


	@Override
	public void followToggle(String id) {
		final User user = userReadPort.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		Optional<UserFollowEntity> followerUserFromDatabase = userFollowRepository
			.findByFollowedUserIdAndFollowerUserId(user.getId(), identity.getUserId());

		if (followerUserFromDatabase.isPresent()) {
			this.unfollowUser(followerUserFromDatabase.get());
			return;
		}

		this.followUser(user);
	}

	private void unfollowUser(UserFollowEntity followerUserFromDatabase) {
		userFollowRepository.delete(followerUserFromDatabase);
	}

	private void followUser(User user) {
		UserFollowEntity userFollowEntity = UserFollowEntity.builder()
			.followerUserId(identity.getUserId())
			.followedUserId(user.getId())
			.build();
		userFollowRepository.save(userFollowEntity);
	}


	@Override
	public void removeFollower(UnfollowRequest request) {

		final User user = userReadPort.findById(request.getFollowerId())
			.orElseThrow(() -> new UserNotFoundByIdException(request.getFollowerId()));

		final UserFollowEntity userFollowEntity = userFollowRepository
			.findByFollowedUserIdAndFollowerUserId(identity.getUserId(), user.getId())
			.orElseThrow(() -> new UserAlreadyUnfollowedException(user.getId(), identity.getUserId()));

		userFollowRepository.delete(userFollowEntity);
	}

}
