package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostMediaEntity;
import org.gelecekbilimde.scienceplatform.post.model.request.PostMediaCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaCreateRequestToPostMediaEntityMapper extends BaseMapper<PostMediaCreateRequest, PostMediaEntity> {

	default PostMediaEntity mapForSaving(PostMediaCreateRequest postMediaCreateRequest, String userId) {
		return PostMediaEntity.builder()
			.postId(postMediaCreateRequest.getPostId())
			.mediaId(postMediaCreateRequest.getMediaId())
			.userId(userId)
			.cover(postMediaCreateRequest.isCover())
			.build();
	}

	static PostMediaCreateRequestToPostMediaEntityMapper initialize() {
		return Mappers.getMapper(PostMediaCreateRequestToPostMediaEntityMapper.class);
	}
}
