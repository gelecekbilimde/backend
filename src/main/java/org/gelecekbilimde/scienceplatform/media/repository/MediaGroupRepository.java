package org.gelecekbilimde.scienceplatform.media.repository;

import org.gelecekbilimde.scienceplatform.media.model.MediaGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaGroupRepository extends JpaRepository<MediaGroup, Integer> {

	Optional<MediaGroup> findById(Long id);
}
