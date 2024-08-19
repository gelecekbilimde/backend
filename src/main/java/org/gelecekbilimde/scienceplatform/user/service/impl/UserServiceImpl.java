package org.gelecekbilimde.scienceplatform.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.exception.NotFoundException;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowerEntity;
import org.gelecekbilimde.scienceplatform.user.repository.UserFollowersRepository;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.gelecekbilimde.scienceplatform.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final Identity identity;
	private final UserRepository userRepository;
	private final UserFollowersRepository userFollowersRepository;

	@Override
	public void followToggle(String id) {
		final UserEntity userEntity = userRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("User could not found! id:" + id));

		Optional<UserFollowerEntity> followerUserFromDatabase = userFollowersRepository.findByFollowedUserIdAndFollowerUserId(id, identity.getUserId());
		if (followerUserFromDatabase.isPresent()) {
			this.unfollowUser(followerUserFromDatabase.get());
			return;
		}
		this.followUser(userEntity);
	}

	private void unfollowUser(UserFollowerEntity followerUserFromDatabase){
		userFollowersRepository.delete(followerUserFromDatabase);
	}

	private void followUser(UserEntity userEntity){
		UserFollowerEntity userFollowerEntity = UserFollowerEntity.builder()
			.followerUserId(identity.getUserId())
			.followedUserId(userEntity.getId())
			.build();
		userFollowersRepository.save(userFollowerEntity);
	}

	public List<UserEntity> getFollowings(String id){
		return this.userFollowersRepository.findFollowersByUserId(id);
	}

	public List<UserEntity> getFollowers(String id){
		return this.userFollowersRepository.findFollowingsByUserId(id);
	}
}
