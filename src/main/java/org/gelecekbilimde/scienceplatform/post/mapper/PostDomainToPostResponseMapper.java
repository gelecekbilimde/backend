package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.Domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.Response.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostDomainToPostResponseMapper extends BaseMapper<PostDomain, PostResponse> {
	@Override
	PostResponse map(PostDomain source);

	static PostDomainToPostResponseMapper initialize() {
		return Mappers.getMapper(PostDomainToPostResponseMapper.class);
	}
}
