package org.gelecekbilimde.scienceplatform.media.repository;

import org.gelecekbilimde.scienceplatform.media.model.entity.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
}
