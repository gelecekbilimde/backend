package org.gelecekbilimde.scienceplatform.post.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostProcessMessage;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostProcessCreate;
import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostProcessCreateToPostProcessModelMapper extends BaseMapper<PostProcessCreate, PostProcess> {

	@Mapping(target = "message", source = "message", qualifiedByName = "convertListToString")
	@Override
	PostProcess map(PostProcessCreate source);

	default PostProcess mapForSaving(PostProcessCreate postProcessCreate, Long userId) {
		String messageString = convertListToString(postProcessCreate.getMessage());
		return PostProcess.builder()
			.header(postProcessCreate.getHeader())
			.content(postProcessCreate.getContent())
			.slug(postProcessCreate.getSlug())
			.process(postProcessCreate.getProcess())
			.postId(postProcessCreate.getPostId())
			.userId(userId)
			.message(messageString)
			.build();
	}

	@Named("convertListToString")
	default String convertListToString(List<PostProcessMessage> postProcessMessages){

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(postProcessMessages);
		}catch (JsonProcessingException e){
			throw new ServerException(e.getMessage());
		}

	}

	static PostProcessCreateToPostProcessModelMapper initialize() {
		return Mappers.getMapper(PostProcessCreateToPostProcessModelMapper.class);
	}
}
