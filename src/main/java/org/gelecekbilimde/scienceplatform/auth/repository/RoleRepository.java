package org.gelecekbilimde.scienceplatform.auth.repository;

import org.gelecekbilimde.scienceplatform.auth.model.Permission;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

	@Query("SELECT r.permissions FROM Role r WHERE r.id = :roleId")
	List<Permission> findPermissionsByRoleId(@Param("roleId") String roleId);

	Optional<Role> getByIsDefaultTrue();

}
