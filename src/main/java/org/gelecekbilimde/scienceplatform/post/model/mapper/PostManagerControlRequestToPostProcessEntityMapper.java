package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostProcessEntity;
import org.gelecekbilimde.scienceplatform.post.model.request.PostManagerControlRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostManagerControlRequestToPostProcessEntityMapper extends BaseMapper<PostManagerControlRequest, PostProcessEntity> {

	default PostProcessEntity mapForSaving(PostManagerControlRequest postManagerControlRequest, String userId, boolean done) {
		return PostProcessEntity.builder()
			.header(postManagerControlRequest.getHeader())
			.content(postManagerControlRequest.getContent())
			.categoryEntity(CategoryEntity.builder()
				.name(postManagerControlRequest.getCategory().getName())
				.orderNumber(postManagerControlRequest.getCategory().getOrderNumber())
				.slug(postManagerControlRequest.getCategory().getSlug())
				.icon(postManagerControlRequest.getCategory().getIcon())
				.parentId(postManagerControlRequest.getCategory().getParentId())
				.build())
			.slug(postManagerControlRequest.getSlug())
			.process(postManagerControlRequest.getProcess())
			.done(done)
			.copyrightControl(postManagerControlRequest.isCopyrightControl())
			.typoControl(postManagerControlRequest.isTypoControl())
			.dangerousControl(postManagerControlRequest.isDangerousControl())
			.userId(userId)
			.build();
	}

	static PostManagerControlRequestToPostProcessEntityMapper initialize() {
		return Mappers.getMapper(PostManagerControlRequestToPostProcessEntityMapper.class);
	}
}
