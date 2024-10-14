package org.gelecekbilimde.scienceplatform.auth.port;

public interface InvalidTokenReadPort {

	boolean existsByTokenId(String tokenId);

}
