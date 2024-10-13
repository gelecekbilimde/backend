package org.gelecekbilimde.scienceplatform.post.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.response.CategorySummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryToSummaryResponseMapper extends BaseMapper<Category, CategorySummaryResponse> {

	static CategoryToSummaryResponseMapper initialize() {
		return Mappers.getMapper(CategoryToSummaryResponseMapper.class);
	}

}
