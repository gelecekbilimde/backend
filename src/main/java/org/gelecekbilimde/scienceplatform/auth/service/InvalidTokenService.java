package org.gelecekbilimde.scienceplatform.auth.service;


import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.gelecekbilimde.scienceplatform.auth.repository.InvalidTokenRepository;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InvalidTokenService {

	final InvalidTokenRepository invalidTokenRepository;

	public void checkForInvalidityOfToken(final String tokenId) {
		final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();
		if (isTokenInvalid) {
			throw new ClientException("Token Kullanılamaz");
		}
	}

	public void saveInvalidToken(String tokenId)
	{
		InvalidToken invalidToken = InvalidToken.builder()
			.tokenId(tokenId)
			.build();
		invalidTokenRepository.save(invalidToken);
	}

}
