package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryCreateRequestToCategoryEntityMapper extends BaseMapper<CategoryCreateRequest, CategoryEntity> {

	static CategoryCreateRequestToCategoryEntityMapper initialize() {
		return Mappers.getMapper(CategoryCreateRequestToCategoryEntityMapper.class);
	}

}
