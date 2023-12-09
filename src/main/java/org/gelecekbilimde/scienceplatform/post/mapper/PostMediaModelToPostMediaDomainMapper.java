package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostMediaDomain;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaModelToPostMediaDomainMapper extends BaseMapper<PostMedia, PostMediaDomain> {

	@Override
	PostMediaDomain map(PostMedia source);
	static PostMediaModelToPostMediaDomainMapper initialize() {
		return Mappers.getMapper(PostMediaModelToPostMediaDomainMapper.class);
	}
}
