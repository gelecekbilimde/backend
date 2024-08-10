package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.entity.InvalidTokenEntity;
import org.gelecekbilimde.scienceplatform.auth.repository.InvalidTokenRepository;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.common.exception.ClientException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class InvalidTokenServiceImpl implements InvalidTokenService {

	final InvalidTokenRepository invalidTokenRepository;

	public void checkForInvalidityOfToken(final String tokenId) {
		final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();
		if (isTokenInvalid) {
			throw new ClientException("Token is invalid");
		}
	}

	public void saveInvalidToken(String tokenId) {
		InvalidTokenEntity invalidTokenEntity = InvalidTokenEntity.builder().tokenId(tokenId).build();
		invalidTokenRepository.save(invalidTokenEntity);
	}

}
