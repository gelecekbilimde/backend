package org.gelecekbilimde.scienceplatform.auth.service;

import java.util.List;

public interface InvalidTokenService {

	void checkForInvalidityOfToken(final String tokenId);

	void saveAll(List<String> invalidTokenIds);

}
