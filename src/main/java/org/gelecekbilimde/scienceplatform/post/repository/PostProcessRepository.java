package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.entity.PostProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostProcessRepository extends JpaRepository<PostProcessEntity, Long> {

	Optional<PostProcessEntity> getTopByPostIdOrderByCreatedAtDesc(String postId);

}
