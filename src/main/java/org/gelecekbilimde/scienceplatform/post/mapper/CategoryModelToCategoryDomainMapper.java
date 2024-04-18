package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryModelToCategoryDomainMapper extends BaseMapper<Category, CategoryDomain> {
	static CategoryModelToCategoryDomainMapper initialize() {
		return Mappers.getMapper(CategoryModelToCategoryDomainMapper.class);
	}
}
