package org.gelecekbilimde.scienceplatform.post.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryModelToCategoryDomainMapper extends BaseMapper<Category, CategoryDomain> {

//	@Override
//	CategoryDomain map(Category source);
//	default List<CategoryDomain> mapCategories(List<Category> categories) {
//		if (categories == null){
//			return new ArrayList<>();
//		}
//		return categories.stream()
//			.map(category ->{
//				List<Category> parentCategories = CategoryRepository.getParentCategories(category.getId());
//				List<String> parentNames = new ArrayList<>();
//				List<Long> parentIds = new ArrayList<>();
//				if (parentCategories != null){
//					parentCategories.forEach(parentCategory -> {
//						parentNames.add(parentCategory.getName());
//						parentIds.add(parentCategory.getId());
//					});
//				}
//				return CategoryDomain.builder()
//					.id(category.getId())
//					.name(category.getName())
//					.parentNames(parentNames)
//					.parentIds(parentIds)
//					.build();
//			})
//			.toList();
//
//	static CategoryModelToCategoryDomainMapper initialize() {
//		return Mappers.getMapper(CategoryModelToCategoryDomainMapper.class);
//	}
}
