package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryCreateRequestToDomainMapper extends BaseMapper<CategoryCreateRequest, Category> {

	static CategoryCreateRequestToDomainMapper initialize() {
		return Mappers.getMapper(CategoryCreateRequestToDomainMapper.class);
	}

}
