package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.post.exception.PostNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostLikeEntity;
import org.gelecekbilimde.scienceplatform.post.repository.PostLikeRepository;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostLikeToggleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
class PostLikeToggleServiceImpl implements PostLikeToggleService {

	private final PostRepository postRepository;
	private final PostLikeRepository postLikeRepository;
	private final Identity identity;

	@Override
	public PostLike toggleLikeOfPost(String id) {

		final PostEntity postEntity = postRepository.findById(id)
			.orElseThrow(() -> new PostNotFoundByIdException(id));

		Optional<PostLikeEntity> postLikeFromDatabase = postLikeRepository.findByPostIdAndUserId(id, identity.getUserId());
		if (postLikeFromDatabase.isPresent()) {
			return this.unlikePost(postEntity, postLikeFromDatabase.get());
		}

		return this.likePost(postEntity);
	}

	private PostLike unlikePost(PostEntity postEntity, PostLikeEntity postLikeEntityFromDatabase) {
		postLikeRepository.delete(postLikeEntityFromDatabase);

		postEntity.unlike();
		postRepository.save(postEntity);

		return PostLike.builder()
			.likeCount(postEntity.getLikeCount())
			.build();
	}

	private PostLike likePost(PostEntity postEntity) {
		PostLikeEntity postLikeEntity = PostLikeEntity.builder()
			.userId(identity.getUserId())
			.postId(postEntity.getId())
			.build();
		postLikeRepository.save(postLikeEntity);

		postEntity.like();
		postRepository.save(postEntity);

		return PostLike.builder()
			.likeCount(postEntity.getLikeCount())
			.build();
	}

}
