package org.gelecekbilimde.scienceplatform.Mapper;


import org.gelecekbilimde.scienceplatform.dto.Post.Business.AdminPostListBusinessDTO;
import org.gelecekbilimde.scienceplatform.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminPostModelToBusinessMapper extends BaseMapper<Post, AdminPostListBusinessDTO> {
	@Override
	AdminPostListBusinessDTO map(Post source);

	static AdminPostModelToBusinessMapper initialize() {
		return Mappers.getMapper(AdminPostModelToBusinessMapper.class);
	}
}
