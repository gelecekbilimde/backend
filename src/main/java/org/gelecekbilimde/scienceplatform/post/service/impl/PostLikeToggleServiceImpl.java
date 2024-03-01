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

import java.util.Optional;

@Service
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

		String userId = identity.getUserId();

		Optional<PostLike> postLikeFromDatabase = postLikeRepository.findByPostIdAndUserId(id, userId);
		if (postLikeFromDatabase.isPresent()) {
			postLikeRepository.delete(postLikeFromDatabase.get());

			post.unlike();
			postRepository.save(post);

			return PostLikeDomain.builder()
				.likeCount(post.getLikeCount())
				.likedAt(postLikeFromDatabase.get().getCreatedAt())
				.build();
		}

		PostLike postLike = postLikeRequestToPostLikeModel.mapForSaving(id, userId);
		PostLike savedPostLike = postLikeRepository.save(postLike);

		post.like();
		postRepository.save(post);

		return PostLikeDomain.builder()
			.likeCount(post.getLikeCount())
			.likedAt(savedPostLike.getCreatedAt())
			.build();
	}

}
