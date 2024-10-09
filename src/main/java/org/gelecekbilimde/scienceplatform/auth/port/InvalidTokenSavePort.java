package org.gelecekbilimde.scienceplatform.auth.port;

import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;

import java.util.List;

public interface InvalidTokenSavePort {

	void saveAll(List<InvalidToken> invalidTokens);

}
