package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RoleApplicationRepository extends JpaRepository<RoleApplicationEntity, String>, JpaSpecificationExecutor<RoleApplicationEntity> {

	Optional<RoleApplicationEntity> findByUserIdAndStatus(String userId, RoleApplicationStatus status);

	boolean existsByUserIdAndStatus(String userId, RoleApplicationStatus status);

}
