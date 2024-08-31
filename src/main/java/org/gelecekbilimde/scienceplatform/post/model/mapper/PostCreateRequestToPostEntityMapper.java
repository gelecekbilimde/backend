package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.post.model.request.PostCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostCreateRequestToPostEntityMapper extends BaseMapper<PostCreateRequest, PostEntity> {

	static PostCreateRequestToPostEntityMapper initialize() {
		return Mappers.getMapper(PostCreateRequestToPostEntityMapper.class);
	}

}
