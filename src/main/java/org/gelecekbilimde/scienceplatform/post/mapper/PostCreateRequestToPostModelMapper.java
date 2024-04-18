package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.common.Util;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostCreateRequestToPostModelMapper extends BaseMapper<PostCreateRequest, Post> {


	default Post mapForSaving(PostCreateRequest postCreateRequest, String userId){
		return Post.builder()
			.id(Util.generateUUID())
			.header(postCreateRequest.getHeader())
			.content(postCreateRequest.getContent())
			.category(postCreateRequest.getCategory())
			.userId(userId)
			.likeCount(postCreateRequest.getLikeCount())
			.active(postCreateRequest.isActive())
			.lastProcess(postCreateRequest.getLastProcess())
			.slug(postCreateRequest.getSlug())
			.build();
	}

	static PostCreateRequestToPostModelMapper initialize() {
		return Mappers.getMapper(PostCreateRequestToPostModelMapper.class);
	}
}
