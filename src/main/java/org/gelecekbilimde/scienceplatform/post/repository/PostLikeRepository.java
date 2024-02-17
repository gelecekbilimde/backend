package org.gelecekbilimde.scienceplatform.post.repository;

import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, String> {

	@Query("SELECT pl FROM PostLike pl WHERE pl.postId = :postId AND pl.userId = :userId")
	PostLike findPostLikeByPostIdAndUserId(@Param("postId") String postId, @Param("userId") String userId);

}
