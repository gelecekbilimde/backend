package org.gelecekbilimde.scienceplatform.user.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.user.model.UserFollow;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserFollowEntity;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserFollowEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserFollowToEntityMapper;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowDeletePort;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserFollowSavePort;
import org.gelecekbilimde.scienceplatform.user.repository.UserFollowRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserFollowAdapter implements UserFollowReadPort, UserFollowSavePort, UserFollowDeletePort {

	private final UserFollowRepository userFollowRepository;


	private final UserFollowEntityToDomainMapper userFollowEntityToDomainMapper = UserFollowEntityToDomainMapper.initialize();
	private final UserFollowToEntityMapper userFollowToEntityMapper = UserFollowToEntityMapper.initialize();


	@Override
	public Optional<UserFollow> findByFollowedUserIdAndFollowerUserId(final String followedUserId,
																	  final String followerUserId) {

		return userFollowRepository.findByFollowedUserIdAndFollowerUserId(followedUserId, followerUserId)
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
