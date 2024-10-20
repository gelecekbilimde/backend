package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.entity.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, Long> {

	boolean existsByTokenId(String tokenId);

}
