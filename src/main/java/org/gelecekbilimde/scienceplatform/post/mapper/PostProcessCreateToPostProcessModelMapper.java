package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostProcessCreate;
import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PostProcessCreateToPostProcessModelMapper extends BaseMapper<PostProcessCreate, PostProcess> {

//	@Mapping(target = "message", source = "message", qualifiedByName = "convertListToString")
	@Override
	PostProcess map(PostProcessCreate source);

	default PostProcess mapForSaving(PostProcessCreate postProcessCreate, Long userId) {
		return PostProcess.builder()
			.header(postProcessCreate.getHeader())
			.content(postProcessCreate.getContent())
			.slug(postProcessCreate.getSlug())
			.process(postProcessCreate.getProcess())
			.postId(postProcessCreate.getPostId())
			.userId(userId)
			.message(postProcessCreate.getMessage())
			.build();
	}

	static PostProcessCreateToPostProcessModelMapper initialize() {
		return Mappers.getMapper(PostProcessCreateToPostProcessModelMapper.class);
	}
}
