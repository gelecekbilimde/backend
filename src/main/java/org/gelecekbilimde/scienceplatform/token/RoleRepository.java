package org.gelecekbilimde.scienceplatform.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT r.permissions FROM Role r WHERE r.role = :role")
	List<Permission> findPermissionsByRole (@Param("role") String role);

	Optional<Role> findByRole( String role);

	Optional<Role> getByIsDefaultTrue();
}
