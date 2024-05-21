package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryToResponseMapper extends BaseMapper<Category, CategoryResponse> {

	static CategoryToResponseMapper initialize() {
		return Mappers.getMapper(CategoryToResponseMapper.class);
	}

}
