package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryAlreadyExistException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryHasChildException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryParentNotFoundException;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryCreateRequestToCategoryEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryEntityToCategoryMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryEntityToCategoryMapper categoryEntityToCategoryMapper = CategoryEntityToCategoryMapper.initialize();
	private final CategoryCreateRequestToCategoryEntityMapper categoryCreateRequestToCategoryEntityMapper = CategoryCreateRequestToCategoryEntityMapper.initialize();

	@Override
	public List<Category> getCategories() {
		return categoryEntityToCategoryMapper.map(categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long id) {
		Optional<CategoryEntity> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException(id);
		}
		return categoryEntityToCategoryMapper.map(category.get());
	}

	@Override
	public void createCategory(CategoryCreateRequest request) {
		if (categoryRepository.existsByName(request.getName())) {
			throw new CategoryAlreadyExistException(request.getName());
		}
		if (request.getParentId() != null && !categoryRepository.existsById(request.getParentId())) {
			throw new CategoryParentNotFoundException(request.getParentId());
		}
		List<CategoryEntity> categories = categoryRepository.findAllByParentId(request.getParentId()).stream().toList();

		for (CategoryEntity categoryEntity : categories) {
			if (categoryEntity.getOrder() >= request.getOrder()) {
				categoryEntity.increaseOrder();
			}
		}

		categoryRepository.save(categoryCreateRequestToCategoryEntityMapper.map(request));
		categoryRepository.saveAll(categories);
	}

	@Override
	public void changeCategoryName(Long id, String newName) {
		CategoryEntity categoryEntity = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		categoryEntity.setName(newName);
		categoryRepository.save(categoryEntity);
	}

	@Override
	public void deleteCategory(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		boolean isCategoryParent = categoryRepository.existsByParentId(id);

		if (isCategoryParent) {
			throw new CategoryHasChildException(id);
		}

		categoryRepository.delete(categoryEntity);
	}
}
