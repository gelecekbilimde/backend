package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryEntityToCategoryMapper extends BaseMapper<CategoryEntity, Category> {
	static CategoryEntityToCategoryMapper initialize() {
		return Mappers.getMapper(CategoryEntityToCategoryMapper.class);
	}

}
