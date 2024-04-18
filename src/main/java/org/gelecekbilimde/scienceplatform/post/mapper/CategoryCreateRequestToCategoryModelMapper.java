package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryCreateRequestToCategoryModelMapper extends BaseMapper<CategoryCreateRequest, Category> {
	static CategoryCreateRequestToCategoryModelMapper initialize() {
		return Mappers.getMapper(CategoryCreateRequestToCategoryModelMapper.class);
	}
}
