package org.gelecekbilimde.scienceplatform.postprocess.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.postprocess.dto.domain.ProcessMessage;
import org.gelecekbilimde.scienceplatform.postprocess.dto.domain.PostProcessCreate;
import org.gelecekbilimde.scienceplatform.postprocess.model.PostProcess;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostProcessCreateToPostProcessModelMapper extends BaseMapper<PostProcessCreate, PostProcess> {

	@Mapping(target = "message", source = "message", qualifiedByName = "convertListToString")
	default PostProcess mapForSaving(PostProcessCreate postProcessCreate, Long userId){
			return PostProcess.builder()
				.header(postProcessCreate.getHeader())
				.content(postProcessCreate.getContent())
				.slug(postProcessCreate.getSlug())
				.process(postProcessCreate.getProcess())
				.postId(postProcessCreate.getPostId())
				.userId(userId)
				.message(convertListToString(postProcessCreate.getMessage()))
				.build();
	}

	@Named("convertListToString")
	default String convertListToString(List<ProcessMessage> processMessages){

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(processMessages);
		}catch (JsonProcessingException e){
			throw new ServerException(e.getMessage());
		}

	}

	static PostProcessCreateToPostProcessModelMapper initialize() {
		return Mappers.getMapper(PostProcessCreateToPostProcessModelMapper.class);
	}
}
