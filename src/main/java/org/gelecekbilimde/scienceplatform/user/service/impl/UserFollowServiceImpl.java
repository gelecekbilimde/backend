package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.user.exception.UserAlreadyUnfollowedException;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserEntityToUserMapper;
import org.gelecekbilimde.scienceplatform.user.model.request.UnfollowRequest;
import org.gelecekbilimde.scienceplatform.user.repository.UserFollowRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserFollowService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFollowServiceImpl implements UserFollowService {

	private final Identity identity;
	private final UserRepository userRepository;
	private final UserFollowRepository userFollowRepository;
	private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

	@Override
	public List<User> findAllFollowings(String id) {
		final UserEntity userEntity = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		return userEntityToUserMapper.map(userEntity.getFollowings());
	}


	@Override
	public List<User> findAllFollowers(String id) {
		final UserEntity userEntity = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		return userEntityToUserMapper.map(userEntity.getFollowers());
	}


	@Override
	public void followToggle(String id) {
		final UserEntity userEntity = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundByIdException(id));

		Optional<UserFollowEntity> followerUserFromDatabase = userFollowRepository
			.findByFollowedUserIdAndFollowerUserId(userEntity.getId(), identity.getUserId());

		if (followerUserFromDatabase.isPresent()) {
			this.unfollowUser(followerUserFromDatabase.get());
			return;
		}

		this.followUser(userEntity);
	}

	private void unfollowUser(UserFollowEntity followerUserFromDatabase) {
		userFollowRepository.delete(followerUserFromDatabase);
	}

	private void followUser(UserEntity userEntity) {
		UserFollowEntity userFollowEntity = UserFollowEntity.builder()
			.followerUserId(identity.getUserId())
			.followedUserId(userEntity.getId())
			.build();
		userFollowRepository.save(userFollowEntity);
	}


	@Override
	public void removeFollower(UnfollowRequest request) {

		final UserEntity userEntity = userRepository.findById(request.getFollowerId())
			.orElseThrow(() -> new UserNotFoundByIdException(request.getFollowerId()));

		final UserFollowEntity userFollowEntity = userFollowRepository
			.findByFollowedUserIdAndFollowerUserId(identity.getUserId(), userEntity.getId())
			.orElseThrow(() -> new UserAlreadyUnfollowedException(userEntity.getId(), identity.getUserId()));

		userFollowRepository.delete(userFollowEntity);
	}
}
