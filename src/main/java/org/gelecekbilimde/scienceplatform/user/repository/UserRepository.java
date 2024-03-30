package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findById(String id);

	boolean existsByEmail(String email);


}
