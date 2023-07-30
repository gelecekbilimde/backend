package org.gelecekbilimde.scienceplatform.repository;

import org.gelecekbilimde.scienceplatform.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Optional<Post> findById(String email);
}
