package org.gelecekbilimde.scienceplatform.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminCategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
//	private static final CategoryCreateRequestToCategoryModelMapper categoryCreateRequestToCategoryModel = CategoryCreateRequestToCategoryModelMapper.initialize();
//	private static final CategoryModelToCategoryDomainMapper categoryModelToCategoryDomain = CategoryModelToCategoryDomainMapper.initialize();


	// TODO: model to domain
	// TODO: domain AdminCategoryResponse

	public void findAll() {
		Set<Category> categories1 = categoryRepository.findCategoryByChildrenId(1L);
		Set<Category> categories2 = categoryRepository.findCategoryByChildrenId(2L);
		Set<Category> categories3 = categoryRepository.findCategoryByChildrenId(3L);
		Set<Category> categories4 = categoryRepository.findCategoryByChildrenId(4L);

		log.info("categories1: {}", categories1);
		log.info("categories2: {}", categories2);
		log.info("categories3: {}", categories3);
		log.info("categories4: {}", categories4);

		log.info("****************************************************************************************************");

		Category category = categoryRepository.findCategoryById(1L);
		Category category2 = categoryRepository.findCategoryById(2L);
		Category category3 = categoryRepository.findCategoryById(3L);
		Category category4 = categoryRepository.findCategoryById(4L);

		log.info("category: {}", category);
		log.info("category2: {}", category2);
		log.info("category3: {}", category3);
		log.info("category4: {}", category4);
	}
	// others...

	public void changeCategoryName(Long categoryId, String newName) {
		try {
			categoryRepository.findCategoryById(categoryId)
				.setName(newName);
			categoryRepository.flush();
		} catch (NullPointerException e) {
			// category not found
		}
	}

	public List<Category> getCategoryListAdmin() {
		return categoryRepository.findAll();
	}



	public Paging<CategoryDomain> getCategoryListAdmin(AdminCategoryListRequest listRequest) {
		return getCategoryListForAdmin(listRequest);
	}

	private Paging<CategoryDomain> getCategoryListForAdmin(AdminCategoryListRequest listRequest) {
		Page<Category> categoryModels = categoryRepository.findAll(listRequest.toPageable());
		List<CategoryDomain> domainDTOList = categoryModelToCategoryDomain(categoryModels.getContent());
		return Paging.of(categoryModels, domainDTOList);
	}


	private List<CategoryDomain> categoryModelToCategoryDomain(List<Category> categories) {
		if (categories == null){
			return new ArrayList<>();
		}
		return categories.stream()
			.map(category ->{
				Set<Category> parentCategories = categoryRepository.findCategoryByChildrenId(category.getId());

				List<String> parentNames = new ArrayList<>();
				List<Long> parentIds = new ArrayList<>();

				if (parentCategories != null){
					parentCategories.forEach(parentCategory -> {
						parentNames.add(parentCategory.getName());
						parentIds.add(parentCategory.getId());
					});
				}

				return CategoryDomain.builder()
					.id(category.getId())
					.name(category.getName())
//					.parentNames(parentNames)
//					.parentIds(parentIds)
					.build();
			})
			.toList();
	}


}
