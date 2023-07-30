package org.gelecekbilimde.scienceplatform.repository;

import org.gelecekbilimde.scienceplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findById(Integer id);

	Boolean existsByEmail(String email);


}
