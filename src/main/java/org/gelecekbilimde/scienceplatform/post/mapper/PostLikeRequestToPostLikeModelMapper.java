package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostLikeRequestToPostLikeModelMapper extends BaseMapper<String, PostLike> {
	default PostLike mapForSaving(String postId, String userId) {
		return PostLike.builder()
			.userId(userId)
			.postId(postId)
			.build();
	}

	static PostLikeRequestToPostLikeModelMapper initialize() {
		return Mappers.getMapper(PostLikeRequestToPostLikeModelMapper.class);
	}
}
