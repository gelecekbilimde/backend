package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.response.CategoriesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryToCategoriesResponseMapper extends BaseMapper<Category, CategoriesResponse> {

	static CategoryToCategoriesResponseMapper initialize() {
		return Mappers.getMapper(CategoryToCategoriesResponseMapper.class);
	}

}
