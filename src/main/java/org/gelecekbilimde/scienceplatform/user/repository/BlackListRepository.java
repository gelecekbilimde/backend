package org.gelecekbilimde.scienceplatform.user.repository;

import org.gelecekbilimde.scienceplatform.user.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlackListRepository extends JpaRepository<BlackList, Integer> {

	Optional<BlackList> findByEmail(String email);

	Boolean existsByEmail(String email);


}
