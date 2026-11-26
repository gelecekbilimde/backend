package org.gelecekbilimde.scienceplatform.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.gelecekbilimde.scienceplatform.auth.model.entity.InvalidTokenEntity;
import org.gelecekbilimde.scienceplatform.auth.model.mapper.InvalidTokenToEntityMapper;
import org.gelecekbilimde.scienceplatform.auth.port.InvalidTokenReadPort;
import org.gelecekbilimde.scienceplatform.auth.port.InvalidTokenSavePort;
import org.gelecekbilimde.scienceplatform.auth.repository.InvalidTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class InvalidTokenAdapter implements InvalidTokenReadPort, InvalidTokenSavePort {

	private final InvalidTokenRepository invalidTokenRepository;


	private final InvalidTokenToEntityMapper invalidTokenToEntityMapper = InvalidTokenToEntityMapper.initialize();


	@Override
	public boolean existsByTokenId(final String tokenId) {
		return invalidTokenRepository.existsByTokenId(tokenId);
	}


	@Override
	public void saveAll(final List<InvalidToken> invalidTokens) {
		final List<InvalidTokenEntity> invalidTokenEntities = invalidTokenToEntityMapper.map(invalidTokens);
		invalidTokenRepository.saveAll(invalidTokenEntities);
	}

}
