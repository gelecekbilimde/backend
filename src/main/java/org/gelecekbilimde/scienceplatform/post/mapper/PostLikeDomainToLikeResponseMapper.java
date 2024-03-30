package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostLikeDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostLikeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostLikeDomainToLikeResponseMapper extends BaseMapper<PostLikeDomain, PostLikeResponse> {

	static PostLikeDomainToLikeResponseMapper initialize() {
		return Mappers.getMapper(PostLikeDomainToLikeResponseMapper.class);
	}

}
