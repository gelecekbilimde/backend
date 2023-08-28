package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.Domain.PostDomain;
import org.gelecekbilimde.scienceplatform.postProcess.dto.Domain.Message;
import org.gelecekbilimde.scienceplatform.postProcess.dto.Domain.PostProcessCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostDomainToPostProcessCreateMapper extends BaseMapper<PostDomain, PostProcessCreate> {

	@Mapping(target = "process", source = "lastProcess")
	@Mapping(target = "postId", source = "id")
	@Mapping(target = "message", source = "source.message",qualifiedByName ="emptyMessage")
	@Override
	PostProcessCreate map(PostDomain source);

	@Named("emptyMessage")
	default List<Message> emptyMessage(){
		return new ArrayList<>();
	}

	static PostDomainToPostProcessCreateMapper initialize() {
		return Mappers.getMapper(PostDomainToPostProcessCreateMapper.class);
	}
}
