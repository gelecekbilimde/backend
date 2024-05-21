package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.post.model.request.PostCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostCreateRequestToPostEntityMapper extends BaseMapper<PostCreateRequest, PostEntity> {

	default PostEntity mapForSaving(PostCreateRequest postCreateRequest, String userId) {
		return PostEntity.builder()
			.id(RandomUtil.generateUUID())
			.header(postCreateRequest.getHeader())
			.content(postCreateRequest.getContent())
			.categoryEntity(CategoryEntity.builder()
				.name(postCreateRequest.getCategory().getName())
				.order(postCreateRequest.getCategory().getOrder())
				.slug(postCreateRequest.getCategory().getSlug())
				.icon(postCreateRequest.getCategory().getIcon())
				.parentId(postCreateRequest.getCategory().getParentId())
				.build())
			.userId(userId)
			.likeCount(postCreateRequest.getLikeCount())
			.active(postCreateRequest.isActive())
			.lastProcess(postCreateRequest.getLastProcess())
			.slug(postCreateRequest.getSlug())
			.build();
	}

	static PostCreateRequestToPostEntityMapper initialize() {
		return Mappers.getMapper(PostCreateRequestToPostEntityMapper.class);
	}

}
