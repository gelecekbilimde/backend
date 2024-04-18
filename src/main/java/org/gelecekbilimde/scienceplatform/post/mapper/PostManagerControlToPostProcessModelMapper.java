package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostManagerControl;
import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostManagerControlToPostProcessModelMapper extends BaseMapper<PostManagerControl, PostProcess> {


	default PostProcess mapForSaving(PostManagerControl postManagerControl, String userId, boolean done){
		return PostProcess.builder()
			.header(postManagerControl.getHeader())
			.content(postManagerControl.getContent())
			.category(postManagerControl.getCategory())
			.slug(postManagerControl.getSlug())
			.process(postManagerControl.getProcess())
			.done(done)
			.copyrightControl(postManagerControl.isCopyrightControl())
			.typoControl(postManagerControl.isTypoControl())
			.dangerousControl(postManagerControl.isDangerousControl())
			.userId(userId)
			.build();
	}

	static PostManagerControlToPostProcessModelMapper initialize() {
		return Mappers.getMapper(PostManagerControlToPostProcessModelMapper.class);
	}
}
