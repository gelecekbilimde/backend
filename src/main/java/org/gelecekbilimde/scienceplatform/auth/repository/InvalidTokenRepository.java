package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Integer> {
	Optional<InvalidToken> findByTokenId(String tokenId);
}
