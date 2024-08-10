package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.entity.PermissionEntity;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {

	@Query("SELECT r.permissionEntities FROM RoleEntity r WHERE r.id = :roleId")
	List<PermissionEntity> findPermissionsByRoleId(@Param("roleId") String roleId);

	Optional<RoleEntity> getByIsDefaultTrue();

}
