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

	/*
		public Paging<CategoryDomain> getCategoryList(CategoryListRequest request) {

			// get all categories
			Page<Category> categoryEntities = categoryRepository.findAll(request.toPageable());

			// convert category entities to category domains
			List<CategoryDomain> categories = categoryModelToCategoryDomain.map(categoryEntities.getContent());

			// get base categories (categories that have no parent)
			List<CategoryDomain> baseCategories = categories.stream()
				.filter(category -> category.getParent() == null)
				.toList();

			// set children of base categories recursively
			for (CategoryDomain baseCategory : baseCategories) {
				setChildrenRecursively(baseCategory, categories);
			}

			return Paging.of(categoryEntities, baseCategories);
		}
	*/
	public List<CategoryDomain> getCategoryList() {
		return categoryModelToCategoryDomain.map(categoryRepository.findAll());
	}

	/*
		public CategoryDomain getCategory(Long categoryId) {
			Category categoryEntity = categoryRepository.findCategoryById(categoryId);
			CategoryDomain categoryDomain = categoryModelToCategoryDomain.map(categoryEntity);

			// get all categories
			List<CategoryDomain> allCategories = categoryModelToCategoryDomain.map(categoryRepository.findAll());

			// set children of category recursively
			setChildrenRecursively(categoryDomain, allCategories);

			return categoryDomain;
		}
	*/
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
		categoryRepository.save(categoryCreateRequestToCategoryModel.map(request));
	}

	public void changeCategoryName(Long categoryId, String newName) {
		try {
			categoryRepository.findCategoryById(categoryId).setName(newName);
		} catch (NullPointerException e) {
			// category not found
			throw CategoryNotFoundException.builder().categoryId(categoryId).build();
		}
	}

	/*
		public void setChildrenRecursively(CategoryDomain parentCategory, List<CategoryDomain> categories) {

			// get children of parent category
			List<CategoryDomain> children =
				categories.stream().filter(category -> category.getParent() != null &&
									category.getParent().getId().equals(parentCategory.getId())).toList();

			// set children of children recursively
			for (CategoryDomain child : children) {
				setChildrenRecursively(child, categories);
			}

			parentCategory.setChildren(children);
		}
	*/
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
