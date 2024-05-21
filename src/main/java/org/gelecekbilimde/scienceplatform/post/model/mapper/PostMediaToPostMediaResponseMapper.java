package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.model.response.PostMediaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaToPostMediaResponseMapper extends BaseMapper<PostMedia, PostMediaResponse> {

	static PostMediaToPostMediaResponseMapper initialize() {
		return Mappers.getMapper(PostMediaToPostMediaResponseMapper.class);
	}

}
