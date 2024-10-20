package org.gelecekbilimde.scienceplatform.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.exception.TokenNotValidException;
import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.gelecekbilimde.scienceplatform.auth.port.InvalidTokenReadPort;
import org.gelecekbilimde.scienceplatform.auth.port.InvalidTokenSavePort;
import org.gelecekbilimde.scienceplatform.auth.service.InvalidTokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class InvalidTokenServiceImpl implements InvalidTokenService {

	private final InvalidTokenReadPort invalidTokenReadPort;
	private final InvalidTokenSavePort invalidTokenSavePort;

	public void checkForInvalidityOfToken(final String tokenId) {

		final boolean isTokenInvalid = invalidTokenReadPort.existsByTokenId(tokenId);

		if (isTokenInvalid) {
			throw new TokenNotValidException();
		}
	}

	public void saveAll(List<String> invalidTokenIds) {

		final List<InvalidToken> invalidTokens = invalidTokenIds.stream()
			.map(invalidTokenId -> InvalidToken.builder().tokenId(invalidTokenId).build())
			.collect(Collectors.toList());

		invalidTokenSavePort.saveAll(invalidTokens);
	}

}
