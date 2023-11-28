package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.model.PostProcess;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostDomainToPostProcessModelMapper extends BaseMapper<PostDomain, PostProcess> {


	default PostProcess mapForSaving(PostDomain postDomain, String userId, boolean done){
		return PostProcess.builder()
			.header(postDomain.getHeader())
			.content(postDomain.getContent())
			.userId(userId)
			.slug(postDomain.getSlug())
			.postId(postDomain.getPostId())
			.process(postDomain.getLastProcess())
			.copyrightControl(postDomain.isCopyrightControl())
			.typoControl(postDomain.isTypoControl())
			.dangerousControl(postDomain.isDangerousControl())
			.done(done)
			.build();
	}



	static PostDomainToPostProcessModelMapper initialize() {
		return Mappers.getMapper(PostDomainToPostProcessModelMapper.class);
	}
}
