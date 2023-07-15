package org.gelecekbilimde.scienceplatform.repository;

import org.gelecekbilimde.scienceplatform.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
