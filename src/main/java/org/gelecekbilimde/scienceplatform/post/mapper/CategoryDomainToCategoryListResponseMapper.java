package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.CategoryListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDomainToCategoryListResponseMapper extends BaseMapper<CategoryDomain, CategoryListResponse> {

	//initialize method
	static CategoryDomainToCategoryListResponseMapper initialize() {
		return Mappers.getMapper(CategoryDomainToCategoryListResponseMapper.class);
	}
}
