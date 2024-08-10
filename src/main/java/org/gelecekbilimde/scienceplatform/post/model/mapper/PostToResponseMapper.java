package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.response.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostToResponseMapper extends BaseMapper<Post, PostResponse> {

	static PostToResponseMapper initialize() {
		return Mappers.getMapper(PostToResponseMapper.class);
	}

}
