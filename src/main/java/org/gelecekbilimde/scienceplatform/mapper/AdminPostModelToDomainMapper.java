package org.gelecekbilimde.scienceplatform.mapper;


import org.gelecekbilimde.scienceplatform.dto.post.Domain.AdminPostListDomainDTO;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPostModelToDomainMapper extends BaseMapper<Post, AdminPostListDomainDTO> {
	@Override
	AdminPostListDomainDTO map(Post source);

	static AdminPostModelToDomainMapper initialize() {
		return Mappers.getMapper(AdminPostModelToDomainMapper.class);
	}
}
