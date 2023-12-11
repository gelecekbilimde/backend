package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

	Post getById(String postId);

	Optional<Post> getByIdAndLastProcess(String postId, Process processEnum);

	boolean existsById(String postId);

	/**
	 * Returns all posts of the given category
	 * @param categoryId: Long
	 * @return List<Post>
	 */
//	List<Post> ....(Long categoryId);

	/**
	 * Returns all posts of the given category and its children
	 * @param categoryId: Long
	 * @return List<Post>
	 */
//	List<Post> ....(Long categoryId);
}
