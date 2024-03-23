package org.gelecekbilimde.scienceplatform.auth.service;

public interface InvalidTokenService {

	void checkForInvalidityOfToken(final String tokenId);

	void saveInvalidToken(String tokenId);
}
