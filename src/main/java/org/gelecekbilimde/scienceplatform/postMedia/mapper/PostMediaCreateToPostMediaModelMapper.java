package org.gelecekbilimde.scienceplatform.postMedia.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.Domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.Request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.postMedia.dto.Request.PostMediaCreate;
import org.gelecekbilimde.scienceplatform.postMedia.model.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaCreateToPostMediaModelMapper extends BaseMapper<PostMediaCreate, PostMedia> {
	default PostMedia mapForSaving(PostMediaCreate postMediaCreate){
		return PostMedia.builder()
			.postId(postMediaCreate.getPostId())
			.mediaId(postMediaCreate.getMediaId())
			.userId(postMediaCreate.getUserId())
			.cover(postMediaCreate.getCover())
			.build();
	}


	static PostMediaCreateToPostMediaModelMapper initialize() {
		return Mappers.getMapper(PostMediaCreateToPostMediaModelMapper.class);
	}
}
