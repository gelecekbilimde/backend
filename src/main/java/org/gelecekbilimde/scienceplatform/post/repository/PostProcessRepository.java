package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostProcessRepository extends JpaRepository<PostProcess, Integer> {
	Optional <PostProcess> getTopByPostIdOrderByCreatedAtDesc(String postId);

	/**
	 * Returns all posts of the given category
	 * @param categoryId: Long
	 * @return List<Post>
	 */
//	List<PostProcess> ....(Long categoryId);

	/**
	 * Returns all posts of the given category and its children
	 * @param categoryId: Long
	 * @return List<Post>
	 */
//	List<PostProcess> ....(Long categoryId);
}
