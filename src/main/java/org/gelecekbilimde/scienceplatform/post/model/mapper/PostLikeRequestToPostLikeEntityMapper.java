package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostLikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostLikeRequestToPostLikeEntityMapper extends BaseMapper<String, PostLikeEntity> { // TODO String'den dönüşüm yapılmamalı ya da komple silinmeli

	default PostLikeEntity mapForSaving(String postId, String userId) {
		return PostLikeEntity.builder()
			.userId(userId)
			.postId(postId)
			.build();
	}

	static PostLikeRequestToPostLikeEntityMapper initialize() {
		return Mappers.getMapper(PostLikeRequestToPostLikeEntityMapper.class);
	}

}
