package org.gelecekbilimde.scienceplatform.media.repository;

import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Integer> {

	Optional<Media> findById(Long id);
}
