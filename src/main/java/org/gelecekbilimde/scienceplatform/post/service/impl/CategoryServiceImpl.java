package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.CategoryAlreadyExistException;
import org.gelecekbilimde.scienceplatform.exception.CategoryHasChildException;
import org.gelecekbilimde.scienceplatform.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.exception.ParentNotFoundException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryCreateRequestToCategoryModelMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryModelToCategoryDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private static final CategoryModelToCategoryDomainMapper categoryModelToCategoryDomain = CategoryModelToCategoryDomainMapper.initialize();
	private static final CategoryCreateRequestToCategoryModelMapper categoryCreateRequestToCategoryModel = CategoryCreateRequestToCategoryModelMapper.initialize();

	@Override
	public List<CategoryDomain> getCategories() {
		return categoryModelToCategoryDomain.map(categoryRepository.findAll());
	}

	@Override
	public CategoryDomain getCategory(Long id) {
		Optional< Category> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException(id);
		}
		return categoryModelToCategoryDomain.map(category.get());
	}

	@Override
	public void createCategory(CategoryCreateRequest request) {
		if (categoryRepository.existsByName(request.getName())) {
			throw new CategoryAlreadyExistException(request.getName());
		}
		if (request.getParentId() != null && !categoryRepository.existsById(request.getParentId())) {
			throw new ParentNotFoundException(request.getParentId());
		}
		List<Category> categories = categoryRepository.findAllByParentId(request.getParentId()).stream().toList();

		for (Category category : categories) {
			if (category.getOrder() >= request.getOrder()) {
				category.increaseOrder();
			}
		}

		categoryRepository.save(categoryCreateRequestToCategoryModel.map(request));
		categoryRepository.saveAll(categories);
	}

	@Override
	public void changeCategoryName(Long id, String newName) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		category.setName(newName);
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		boolean isCategoryParent = categoryRepository.existsByParentId(id);

		if (isCategoryParent) {
			throw new CategoryHasChildException(id);
		}

		categoryRepository.delete(category);
	}
}
