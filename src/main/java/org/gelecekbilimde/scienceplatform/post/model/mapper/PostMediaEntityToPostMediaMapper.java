package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostMediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaEntityToPostMediaMapper extends BaseMapper<PostMediaEntity, PostMedia> {

	static PostMediaEntityToPostMediaMapper initialize() {
		return Mappers.getMapper(PostMediaEntityToPostMediaMapper.class);
	}

}
