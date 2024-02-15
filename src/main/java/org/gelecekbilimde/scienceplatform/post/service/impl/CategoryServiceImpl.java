package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.exception.CategoryAlreadyExist;
import org.gelecekbilimde.scienceplatform.exception.CategoryHasChild;
import org.gelecekbilimde.scienceplatform.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryCreateRequestToCategoryModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryModelToCategoryDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private static final CategoryModelToCategoryDomainMapper categoryModelToCategoryDomain = CategoryModelToCategoryDomainMapper.initialize();
	private static final CategoryCreateRequestToCategoryModelMapper categoryCreateRequestToCategoryModel = CategoryCreateRequestToCategoryModelMapper.initialize();

	public List<CategoryDomain> getCategoryList() {
		return categoryModelToCategoryDomain.map(categoryRepository.findAll());
	}

	public CategoryDomain getCategory(Long categoryId) {
		Category categoryEntity = categoryRepository.findCategoryById(categoryId);
		if (categoryEntity == null) {
			throw CategoryNotFoundException.builder().categoryId(categoryId).build();
		}
		return categoryModelToCategoryDomain.map(categoryEntity);
	}

	public void createCategory(CategoryCreateRequest request) {
		if (categoryRepository.findCategoryByName(request.getName()) != null) {
			throw CategoryAlreadyExist.builder().categoryName(request.getName()).build();
		}
		if (request.getParentId() != null && categoryRepository.findCategoryById(request.getParentId()) == null) {
			throw CategoryNotFoundException.builder().categoryId(request.getParentId()).build();
		}
		List<Category> categories = categoryRepository.findCategoriesByParentId(request.getParentId()).stream().toList();

		for (Category category : categories) {
			if (category.getOrder() >= request.getOrder()) {
				category.setOrder(category.getOrder() + 1);
			}
		}

		categoryRepository.save(categoryCreateRequestToCategoryModel.map(request));
		categoryRepository.saveAll(categories);
	}

	public void changeCategoryName(Long categoryId, String newName) {
		try {
			categoryRepository.findCategoryById(categoryId).setName(newName);
		} catch (NullPointerException e) {
			throw CategoryNotFoundException.builder().categoryId(categoryId).build();
		}
	}

	public void deleteCategory(Long categoryId) {
		Category category = categoryRepository.findCategoryById(categoryId);
		if (category == null) {
			throw CategoryNotFoundException.builder().categoryId(categoryId).build();
		}
		if (categoryRepository.findCategoriesByParentId(categoryId).isEmpty()) {
			categoryRepository.delete(category);
		} else {
			throw CategoryHasChild.builder().categoryId(categoryId).build();
		}
	}
}
