package org.gelecekbilimde.scienceplatform.postProcess.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.exception.ServerException;
import org.gelecekbilimde.scienceplatform.postProcess.dto.Domain.PostProcessCreate;
import org.gelecekbilimde.scienceplatform.postProcess.model.PostProcess;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostProcessCreateToPostProcessModelMapper extends BaseMapper<PostProcessCreate, PostProcess> {

	default PostProcess mapForSaving(PostProcessCreate postProcessCreate, Long userId){

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String message = objectMapper.writeValueAsString(postProcessCreate.getMessage());

			return PostProcess.builder()
				.header(postProcessCreate.getHeader())
				.content(postProcessCreate.getContent())
				.slug(postProcessCreate.getSlug())
				.process(postProcessCreate.getProcess())
				.postId(postProcessCreate.getPostId())
				.userId(userId)
				.message(message)
				.build();

		}catch (JsonProcessingException e){
			throw new ServerException(e.getMessage());
		}
	}

	static PostProcessCreateToPostProcessModelMapper initialize() {
		return Mappers.getMapper(PostProcessCreateToPostProcessModelMapper.class);
	}
}
