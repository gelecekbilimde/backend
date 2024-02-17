package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostLikeRequest;
import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface PostLikeRequestToPostLikeModelMapper extends BaseMapper<PostLikeRequest, PostLike> {
	default PostLike mapForSaving(PostLikeRequest postLikeRequest, String userId) {
		return PostLike.builder()
			.id(Util.generateUUID())
			.userId(userId)
			.createdAt(LocalDateTime.now())
			.postId(postLikeRequest.getPostId())
			.build();
	}

	static PostLikeRequestToPostLikeModelMapper initialize() {
		return Mappers.getMapper(PostLikeRequestToPostLikeModelMapper.class);
	}
}
