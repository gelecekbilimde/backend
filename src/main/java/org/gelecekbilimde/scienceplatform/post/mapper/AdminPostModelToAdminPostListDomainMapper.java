package org.gelecekbilimde.scienceplatform.post.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.Domain.AdminPostListDomain;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPostModelToAdminPostListDomainMapper extends BaseMapper<Post, AdminPostListDomain> {
	@Override
    AdminPostListDomain map(Post source);

	static AdminPostModelToAdminPostListDomainMapper initialize() {
		return Mappers.getMapper(AdminPostModelToAdminPostListDomainMapper.class);
	}
}
