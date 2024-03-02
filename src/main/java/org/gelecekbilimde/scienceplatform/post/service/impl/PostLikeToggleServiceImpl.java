package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.NotFoundException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostLikeDomain;
import org.gelecekbilimde.scienceplatform.post.mapper.PostLikeRequestToPostLikeModelMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.gelecekbilimde.scienceplatform.post.repository.PostLikeRepository;
import org.gelecekbilimde.scienceplatform.post.repository.PostRepository;
import org.gelecekbilimde.scienceplatform.post.service.PostLikeToggleService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
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

	private static final PostLikeRequestToPostLikeModelMapper postLikeRequestToPostLikeModel = PostLikeRequestToPostLikeModelMapper.initialize();

	@Override
	public PostLikeDomain toggleLikeOfPost(String id) {

		final Post post = postRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Post not found! id:" + id));

		Optional<PostLike> postLikeFromDatabase = postLikeRepository.findByPostIdAndUserId(id, identity.getUserId());
		if (postLikeFromDatabase.isPresent()) {
			return this.unlikePost(post, postLikeFromDatabase.get());
		}

		return this.likePost(post);
	}

	private PostLikeDomain unlikePost(Post post, PostLike postLikeFromDatabase) {
		postLikeRepository.delete(postLikeFromDatabase);

		post.unlike();
		postRepository.save(post);

		return PostLikeDomain.builder()
			.likeCount(post.getLikeCount())
			.likedAt(postLikeFromDatabase.getCreatedAt())
			.build();
	}

	private PostLikeDomain likePost(Post post) {
		PostLike postLike = postLikeRequestToPostLikeModel.mapForSaving(post.getId(), identity.getUserId());
		PostLike savedPostLike = postLikeRepository.save(postLike);

		post.like();
		postRepository.save(post);

		return PostLikeDomain.builder()
			.likeCount(post.getLikeCount())
			.likedAt(savedPostLike.getCreatedAt())
			.build();
	}

}
