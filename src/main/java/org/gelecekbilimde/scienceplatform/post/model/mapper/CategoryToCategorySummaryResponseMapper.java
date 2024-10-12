package org.gelecekbilimde.scienceplatform.post.model.mapper;


import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.response.CategorySummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryToCategorySummaryResponseMapper extends BaseMapper <Category, CategorySummaryResponse> {
	static CategoryToCategorySummaryResponseMapper initialize() {
		return Mappers.getMapper(CategoryToCategorySummaryResponseMapper.class);
	}
}
