package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.PostLike;
import org.gelecekbilimde.scienceplatform.post.model.response.PostLikeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostLikeToResponseMapper extends BaseMapper<PostLike, PostLikeResponse> {

	static PostLikeToResponseMapper initialize() {
		return Mappers.getMapper(PostLikeToResponseMapper.class);
	}

}
