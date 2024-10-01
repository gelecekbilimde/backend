package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleApplicationRepository extends JpaRepository<RoleApplicationEntity, Long>, JpaSpecificationExecutor<RoleApplicationEntity> {

	boolean existsByUserAndStatus(UserEntity user, RoleApplicationStatus status);

}
