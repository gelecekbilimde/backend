package org.gelecekbilimde.scienceplatform.user.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.user.model.User;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserEntityToUserMapper;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserToEntityMapper;
import org.gelecekbilimde.scienceplatform.user.port.UserReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserSavePort;
import org.gelecekbilimde.scienceplatform.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserAdapter implements UserReadPort, UserSavePort {

	private final UserRepository userRepository;


	private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();
	private final UserToEntityMapper userToEntityMapper = UserToEntityMapper.initialize();


	@Override
	public Optional<User> findById(final String id) {
		return userRepository.findById(id)
			.map(userEntityToUserMapper::map);
	}


	@Override
	public Optional<User> findByEmail(final String email) {
		return userRepository.findByEmail(email)
			.map(userEntityToUserMapper::map);
	}


	@Override
	public boolean existsByEmail(final String email) {
		return userRepository.existsByEmail(email);
	}


	@Override
	public User save(User user) {
		final UserEntity userEntity = userToEntityMapper.map(user);
		final UserEntity savedUserEntity = userRepository.save(userEntity);
		return userEntityToUserMapper.map(savedUserEntity);
	}

}
