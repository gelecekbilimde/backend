package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.TokenNotValidException;
import org.gelecekbilimde.scienceplatform.auth.model.entity.InvalidTokenEntity;
import org.gelecekbilimde.scienceplatform.auth.repository.InvalidTokenRepository;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class InvalidTokenServiceImpl implements InvalidTokenService {

	private final InvalidTokenRepository invalidTokenRepository;

	public void checkForInvalidityOfToken(final String tokenId) {
		final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();
		if (isTokenInvalid) {
			throw new TokenNotValidException();
		}
	}

	public void saveAll(List<String> invalidTokenIds) {
		List<InvalidTokenEntity> invalidTokenEntities = invalidTokenIds.stream()
			.map(invalidTokenId -> InvalidTokenEntity.builder().tokenId(invalidTokenId).build())
			.collect(Collectors.toUnmodifiableList());
		invalidTokenRepository.saveAll(invalidTokenEntities);
	}

}
