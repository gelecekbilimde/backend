package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.AdminPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostDomainToAdminPostResponseMapper extends BaseMapper<PostDomain, AdminPostResponse> {


	@Mapping(target = "user.userId", source = "user.id")
	@Override
	AdminPostResponse map(PostDomain source);

	static PostDomainToAdminPostResponseMapper initialize() {
		return Mappers.getMapper(PostDomainToAdminPostResponseMapper.class);
	}
}
