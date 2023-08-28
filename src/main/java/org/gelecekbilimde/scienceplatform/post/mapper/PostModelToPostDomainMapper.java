package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.Domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostModelToPostDomainMapper extends BaseMapper<Post, PostDomain> {
	@Override
	PostDomain map(Post source);

	static PostModelToPostDomainMapper initialize() {
		return Mappers.getMapper(PostModelToPostDomainMapper.class);
	}
}
