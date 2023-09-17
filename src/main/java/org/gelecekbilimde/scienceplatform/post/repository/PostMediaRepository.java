package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostMediaRepository extends JpaRepository<PostMedia, Integer> {

	Optional<PostMedia> findById(Integer id);
}
