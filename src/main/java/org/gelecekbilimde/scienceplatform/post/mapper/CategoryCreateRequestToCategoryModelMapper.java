package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.mapstruct.Mapper;


@Mapper
public interface CategoryCreateRequestToCategoryModelMapper extends BaseMapper<CategoryDomain, Category> {

//	@Override
//	CategoryDomain map(Category source);
//	default List<CategoryDomain> map(List<Category> source) {
//		if (source == null){
//			return new ArrayList<>();
//		}
//		return source.stream()
//			.map(this::map)
//			.toList();
//	}
//
//	static CategoryCreateRequestToCategoryModelMapper initialize() {
//		return Mappers.getMapper(CategoryCreateRequestToCategoryModelMapper.class);
//	}
//

}
