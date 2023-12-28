package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.response.AdminCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDomainToAdminCategoryResponseMapper extends BaseMapper<CategoryDomain, AdminCategoryResponse> {

	static CategoryDomainToAdminCategoryResponseMapper initialize() {
		return Mappers.getMapper(CategoryDomainToAdminCategoryResponseMapper.class);
	}
}

