package org.gelecekbilimde.scienceplatform.postmedia.repository;

import org.gelecekbilimde.scienceplatform.postmedia.model.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostMediaRepository extends JpaRepository<PostMedia, Integer> {

	Optional<PostMedia> findById(Integer id);
}
