package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.entity.AuthorRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRequestRepository extends JpaRepository<AuthorRequestEntity,String> {
    Optional<AuthorRequestEntity> findByUserId(String id);
}
