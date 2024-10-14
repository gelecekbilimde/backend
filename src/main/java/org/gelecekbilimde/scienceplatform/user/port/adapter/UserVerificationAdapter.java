package org.gelecekbilimde.scienceplatform.user.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.user.model.UserVerification;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserVerificationEntity;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserVerificationEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.user.model.mapper.UserVerificationToEntityMapper;
import org.gelecekbilimde.scienceplatform.user.port.UserVerificationReadPort;
import org.gelecekbilimde.scienceplatform.user.port.UserVerificationSavePort;
import org.gelecekbilimde.scienceplatform.user.repository.UserVerificationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserVerificationAdapter implements UserVerificationReadPort, UserVerificationSavePort {

	private final UserVerificationRepository userVerificationRepository;


	private final UserVerificationEntityToDomainMapper userVerificationEntityToDomainMapper = UserVerificationEntityToDomainMapper.initialize();
	private final UserVerificationToEntityMapper userVerificationToEntityMapper = UserVerificationToEntityMapper.initialize();


	@Override
	public Optional<UserVerification> findById(final String id) {
		return userVerificationRepository.findById(id)
			.map(userVerificationEntityToDomainMapper::map);
	}


	@Override
	public UserVerification save(final UserVerification userVerification) {
		final UserVerificationEntity userVerificationEntity = userVerificationToEntityMapper.map(userVerification);
		final UserVerificationEntity savedUserVerificationEntity = userVerificationRepository.save(userVerificationEntity);
		return userVerificationEntityToDomainMapper.map(savedUserVerificationEntity);
	}

}
