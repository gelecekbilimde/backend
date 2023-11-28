package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostMediaDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostMediaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaDomainToPostMediaResponseMapper extends BaseMapper<PostMediaDomain, PostMediaResponse> {

	@Override
	PostMediaResponse map(PostMediaDomain source);
	static PostMediaDomainToPostMediaResponseMapper initialize() {
		return Mappers.getMapper(PostMediaDomainToPostMediaResponseMapper.class);
	}
}
