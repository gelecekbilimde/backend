package org.gelecekbilimde.scienceplatform.user.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.UserFollow;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserFollowEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserFollowToEntityMapper;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserToEntityMapper;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowDeletePort;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowSavePort;
import org.gelecekbilimde.scienceplatform.user.repository.UserFollowRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserFollowAdapter implements UserFollowReadPort, UserFollowSavePort, UserFollowDeletePort {

	private final UserFollowRepository userFollowRepository;

	private final UserToEntityMapper userToEntityMapper = UserToEntityMapper.initialize();
	private final UserFollowEntityToDomainMapper userFollowEntityToDomainMapper = UserFollowEntityToDomainMapper.initialize();
	private final UserFollowToEntityMapper userFollowToEntityMapper = UserFollowToEntityMapper.initialize();


	@Override
	public List<UserFollow> findAllByFollowed(User followed) {
		final UserEntity followedEntity = userToEntityMapper.map(followed);
		return userFollowRepository.findAllByFollowed(followedEntity)
			.stream()
			.map(userFollowEntityToDomainMapper::map)
			.toList();
	}


	@Override
	public List<UserFollow> findAllByFollower(User follower) {
		final UserEntity followerEntity = userToEntityMapper.map(follower);
		return userFollowRepository.findAllByFollower(followerEntity)
			.stream()
			.map(userFollowEntityToDomainMapper::map)
			.toList();
	}


	@Override
	public Optional<UserFollow> findByFollowedAndFollower(final User followed,
														  final User follower) {

		final UserEntity followedEntity = userToEntityMapper.map(followed);
		final UserEntity followerEntity = userToEntityMapper.map(follower);
		return userFollowRepository.findByFollowedAndFollower(followedEntity, followerEntity)
			.map(userFollowEntityToDomainMapper::map);
	}


	@Override
	public void save(final UserFollow userFollow) {
		final UserFollowEntity userFollowEntity = userFollowToEntityMapper.map(userFollow);
		userFollowRepository.save(userFollowEntity);
	}


	@Override
	public void delete(final UserFollow userFollow) {
		final UserFollowEntity userFollowEntity = userFollowToEntityMapper.map(userFollow);
		userFollowRepository.delete(userFollowEntity);
	}

}
