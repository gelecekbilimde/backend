package org.gelecekbilimde.scienceplatform.repository;

import org.gelecekbilimde.scienceplatform.model.MediaGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaGroupRepository extends JpaRepository<MediaGroup, Integer> {

	Optional<MediaGroup> findById(Long id);
}
