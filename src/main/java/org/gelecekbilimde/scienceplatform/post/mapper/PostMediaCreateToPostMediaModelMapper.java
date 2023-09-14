package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostMediaCreate;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaCreateToPostMediaModelMapper extends BaseMapper<PostMediaCreate, PostMedia> {
	default PostMedia mapForSaving(PostMediaCreate postMediaCreate, Long userId){
		return PostMedia.builder()
			.postId(postMediaCreate.getPostId())
			.mediaId(postMediaCreate.getMediaId())
			.userId(userId)
			.cover(postMediaCreate.isCover())
			.build();
	}


	static PostMediaCreateToPostMediaModelMapper initialize() {
		return Mappers.getMapper(PostMediaCreateToPostMediaModelMapper.class);
	}
}
