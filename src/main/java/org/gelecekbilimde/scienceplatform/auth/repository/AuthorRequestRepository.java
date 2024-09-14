package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.entity.AuthorRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRequestRepository extends JpaRepository<AuthorRequestEntity,String> {
    Optional<AuthorRequestEntity> findByUserId(String id);
}
