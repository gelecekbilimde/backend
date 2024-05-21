package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.response.AdminPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostToAdminPostResponseMapper extends BaseMapper<Post, AdminPostResponse> {

	static PostToAdminPostResponseMapper initialize() {
		return Mappers.getMapper(PostToAdminPostResponseMapper.class);
	}

}
