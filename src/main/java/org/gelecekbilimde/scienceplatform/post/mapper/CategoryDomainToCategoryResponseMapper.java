package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDomainToCategoryResponseMapper extends BaseMapper<CategoryDomain, CategoryResponse> {
	static CategoryDomainToCategoryResponseMapper initialize() {
		return Mappers.getMapper(CategoryDomainToCategoryResponseMapper.class);
	}
}
