package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.auth.model.Permission;
import org.gelecekbilimde.scienceplatform.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT r.permissions FROM Role r WHERE r.name = :role")
	List<Permission> findPermissionsByName (@Param("role") String role);

	Optional<Role> findByName( String name);

	Optional<Role> getByIsDefaultTrue();
}
