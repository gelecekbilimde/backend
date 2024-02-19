package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationRepository extends JpaRepository<UserVerification, String> {
}
