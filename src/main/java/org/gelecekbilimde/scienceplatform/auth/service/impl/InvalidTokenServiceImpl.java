package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.gelecekbilimde.scienceplatform.auth.repository.InvalidTokenRepository;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.springframework.stereotype.Service;

@Slf4j
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
		InvalidToken invalidToken = InvalidToken.builder().tokenId(tokenId).build();
		invalidTokenRepository.save(invalidToken);
	}

}
