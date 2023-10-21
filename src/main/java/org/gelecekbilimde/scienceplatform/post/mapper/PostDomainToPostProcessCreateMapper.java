package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostProcessCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PostDomainToPostProcessCreateMapper extends BaseMapper<PostDomain, PostProcessCreate> {

	@Mapping(target = "process", source = "lastProcess")
//	@Mapping(target = "postId", source = "id")
//	@Mapping(target = "message",expression ="java(emptyMessage())")
	@Override
	PostProcessCreate map(PostDomain source);

	static PostDomainToPostProcessCreateMapper initialize() {
		return Mappers.getMapper(PostDomainToPostProcessCreateMapper.class);
	}
}
