package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostProcessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostToPostProcessEntityMapper extends BaseMapper<Post, PostProcessEntity> {

	default PostProcessEntity mapForSaving(Post post, String userId, boolean done) {
		return PostProcessEntity.builder()
			.header(post.getHeader())
			.content(post.getContent())
			.categoryEntity(post.getCategoryEntity())
			.userId(userId)
			.slug(post.getSlug())
			.postId(post.getId())
			.process(post.getLastProcess())
			.copyrightControl(post.isCopyrightControl())
			.typoControl(post.isTypoControl())
			.dangerousControl(post.isDangerousControl())
			.done(done)
			.build();
	}


	static PostToPostProcessEntityMapper initialize() {
		return Mappers.getMapper(PostToPostProcessEntityMapper.class);
	}

}
