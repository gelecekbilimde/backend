package org.gelecekbilimde.scienceplatform.postmedia.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.postmedia.dto.request.PostMediaCreate;
import org.gelecekbilimde.scienceplatform.postmedia.model.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaCreateToPostMediaModelMapper extends BaseMapper<PostMediaCreate, PostMedia> {
	default PostMedia mapForSaving(PostMediaCreate postMediaCreate, Long userId){
		return PostMedia.builder()
			.postId(postMediaCreate.getPostId())
			.mediaId(postMediaCreate.getMediaId())
			.userId(userId)
			.cover(postMediaCreate.getCover())
			.build();
	}


	static PostMediaCreateToPostMediaModelMapper initialize() {
		return Mappers.getMapper(PostMediaCreateToPostMediaModelMapper.class);
	}
}
