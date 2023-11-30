package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.gelecekbilimde.scienceplatform.media.model.MediaGroup;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostMediaDomain;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostModelToPostDomainMapper extends BaseMapper<Post, PostDomain> {

	@Mapping(target = "postId", source = "id")

	@Override
	PostDomain map(Post source);
	default List<PostMediaDomain> mapMedias(List<PostMedia> medias) {

		if (medias == null){
			return new ArrayList<>();
		}

		return medias.stream()
			.map(media ->{
				Media mediaData = media.getMedia();
				MediaGroup mediaGroup = mediaData.getMediaGroup();
				return PostMediaDomain.builder()
					.id(media.getId())
					.mediaId(media.getMediaId())
					.cover(media.isCover())
					.url(mediaData.getUrl())
					.contentType(mediaData.getContentType())
					.title(mediaData.getTitle())
					.shared(mediaData.isShared())
					.groupId(mediaGroup.getId())
					.groupName(mediaGroup.getName())
					.createdAt(media.getCreatedAt())
					.build();
			})
			.toList();
	}
	static PostModelToPostDomainMapper initialize() {
		return Mappers.getMapper(PostModelToPostDomainMapper.class);
	}
}
