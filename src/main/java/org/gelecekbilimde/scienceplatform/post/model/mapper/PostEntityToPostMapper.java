package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.media.model.entity.MediaEntity;
import org.gelecekbilimde.scienceplatform.media.model.entity.MediaGroupEntity;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.PostMedia;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostMediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostEntityToPostMapper extends BaseMapper<PostEntity, Post> {

	// TODO PostMediaEntity ve PostMedia içerisindeki isimlendirmeler aynı olduğu sürece bu metoda ihtiyaç olmamalı
	default List<PostMedia> mapMedias(List<PostMediaEntity> medias) {

		if (medias == null) {
			return new ArrayList<>();
		}

		return (List<PostMedia>) medias.stream()
			.map(media -> {
				MediaEntity mediaEntityData = media.getMediaEntity();
				MediaGroupEntity mediaGroupEntity = mediaEntityData.getMediaGroupEntity();
				return PostMedia.builder()
					.id(media.getId())
					.mediaId(media.getMediaId())
					.cover(media.isCover())
					.url(mediaEntityData.getUrl())
					.contentType(mediaEntityData.getContentType())
					.title(mediaEntityData.getTitle())
					.shared(mediaEntityData.isShared())
					.groupId(mediaGroupEntity.getId())
					.groupName(mediaGroupEntity.getName())
					.createdAt(media.getCreatedAt())
					.build();
			})
			.toList();
	}

	static PostEntityToPostMapper initialize() {
		return Mappers.getMapper(PostEntityToPostMapper.class);
	}
}
