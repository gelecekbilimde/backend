package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.entity.AuthorRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRequestRepository extends JpaRepository<AuthorRequestEntity,String> {
}
