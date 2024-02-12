package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryCreateRequestToCategoryModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryModelToCategoryDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	//	private static final CategoryCreateRequestToCategoryModelMapper categoryCreateRequestToCategoryModel = CategoryCreateRequestToCategoryModelMapper.initialize();
	private static final CategoryModelToCategoryDomainMapper categoryModelToCategoryDomain = CategoryModelToCategoryDomainMapper.initialize();
	private static final CategoryCreateRequestToCategoryModelMapper categoryCreateRequestToCategoryModel = CategoryCreateRequestToCategoryModelMapper.initialize();

	public Paging<CategoryDomain> getCategoryList(CategoryListRequest request) {
		Page<Category> categoryEntities = categoryRepository.findAll(request.toPageable());
		List<CategoryDomain> categories = categoryModelToCategoryDomain.map(categoryEntities.getContent());

		List<CategoryDomain> baseCategories = categories.stream()
			.filter(category -> category.getParent() == null)
			.collect(Collectors.toList());

		for (CategoryDomain baseCategory : baseCategories) {
			setChildrenRecursively(baseCategory, categories);
		}
		return Paging.of(categoryEntities, baseCategories);
	}

	public void setChildrenRecursively(CategoryDomain parentCategory, List<CategoryDomain> categories) {
		List<CategoryDomain> children = categories.stream()
			.filter(category -> category.getParent() != null && category.getParent().getId().equals(parentCategory.getId()))
			.collect(Collectors.toList());

		for (CategoryDomain child : children) {
			setChildrenRecursively(child, categories);
		}

		parentCategory.setChildren(children);
	}


	public CategoryDomain getCategory(Long categoryId) {
		Category category = categoryRepository.findCategoryById(categoryId);
		log.info("category: {}", category);
		return categoryModelToCategoryDomain.map(category);
	}

	public void createCategory(CategoryCreateRequest request) {
		if (categoryRepository.findCategoryByName(request.getName()) != null) {
			// category already exists
			log.warn("category already exists with name: {}", categoryRepository.findCategoryByName(request.getName()));
			return;
		}
		Category category = categoryCreateRequestToCategoryModel.map(request);
		categoryRepository.save(category);
		log.info("new category: {}", categoryRepository.findCategoryByName(request.getName()));
	}

//	private void findCategoriesById() {
//		Set<Category> categories1 = categoryRepository.findCategoryByChildrenId(1L);
//		Set<Category> categories2 = categoryRepository.findCategoryByChildrenId(2L);
//		Set<Category> categories3 = categoryRepository.findCategoryByChildrenId(3L);
//		Set<Category> categories4 = categoryRepository.findCategoryByChildrenId(4L);
//
//		log.info("categories1: {}", categories1);
//		log.info("categories2: {}", categories2);
//		log.info("categories3: {}", categories3);
//		log.info("categories4: {}", categories4);
//
//		log.info("****************************************************************************************************");
//
//		Category category = categoryRepository.findCategoryById(1L);
//		Category category2 = categoryRepository.findCategoryById(2L);
//		Category category3 = categoryRepository.findCategoryById(3L);
//		Category category4 = categoryRepository.findCategoryById(4L);
//
//		log.info("category: {}", category);
//		log.info("category2: {}", category2);
//		log.info("category3: {}", category3);
//		log.info("category4: {}", category4);
//	}

	public void changeCategoryName(Long categoryId, String newName) {
		try {
			categoryRepository.findCategoryById(categoryId).setName(newName);
			categoryRepository.flush();
		} catch (NullPointerException e) {
			// category not found
		}
	}

//	@Override
//	public Paging<CategoryDomain> getCategoryList(CategoryListRequest listRequest) {
//		Page<Category> categoryModels = categoryRepository.findAll(listRequest.toPageable());
//		List<CategoryDomain> categoryDomains = categoryModelToCategoryDomain(categoryModels.getContent())
//		return Paging.of(categoryModels, categoryDomains);
//		// TODO: neden bu şekilde dönüş yapılıyor?
//	}

//	private List<CategoryDomain> categoryModelToCategoryDomain(List<Category> categories) {
//		if (categories == null) {
//			return new ArrayList<>();
//		}
//		return categories.stream()
//			.map(category -> {
//				Set<Category> parentCategories = categoryRepository.findCategoryByChildrenId(category.getId());
//
//				List<String> parentNames = new ArrayList<>();
//				List<Long> parentIds = new ArrayList<>();
//
//				if (parentCategories != null) {
//					parentCategories.forEach(parentCategory -> {
//						parentNames.add(parentCategory.getName());
//						parentIds.add(parentCategory.getId());
//					});
//				}
//
//				return CategoryDomain.builder()
//					.id(category.getId())
//					.name(category.getName())
////					.parentNames(parentNames)
////					.parentIds(parentIds)
//					.build();
//			})
//			.toList();
//	}


}
