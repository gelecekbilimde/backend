package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaCreateToPostMediaModelMapper extends BaseMapper<PostMediaCreateRequest, PostMedia> {
	default PostMedia mapForSaving(PostMediaCreateRequest postMediaCreateRequest, String userId){
		return PostMedia.builder()
			.postId(postMediaCreateRequest.getPostId())
			.mediaId(postMediaCreateRequest.getMediaId())
			.userId(userId)
			.cover(postMediaCreateRequest.isCover())
			.build();
	}


	static PostMediaCreateToPostMediaModelMapper initialize() {
		return Mappers.getMapper(PostMediaCreateToPostMediaModelMapper.class);
	}
}
