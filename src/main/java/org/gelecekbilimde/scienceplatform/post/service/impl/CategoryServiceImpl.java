package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.media.util.SlugUtil;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryAlreadyExistException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryHasChildException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryParentNotFoundException;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryCreateRequestToCategoryEntityMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryEntityToCategoryMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	public BasePage<Category> findAll(final CategoryListRequest listRequest) {

		final Pageable pageable = listRequest.getPageable().toPageable();

		final Page<CategoryEntity> categoriesPage = categoryRepository
			.findAll(Specification.allOf(), pageable);

		final List<Category> categories = categoryEntityToCategoryMapper
			.map(categoriesPage.getContent());

		return BasePage.of(
			categoriesPage,
			categories
		);
	}


	@Override
	public List<Category> findAll() {
		List<CategoryEntity> categories = categoryRepository.findAll();
		return categoryEntityToCategoryMapper.map(categories);
	}


	@Override
	public Category findById(Long id) {
		Optional<CategoryEntity> category = categoryRepository.findById(id);
		if (category.isEmpty()) {
			throw new CategoryNotFoundException(id);
		}
		return categoryEntityToCategoryMapper.map(category.get());
	}


	@Override
	public void create(CategoryCreateRequest request) {

		final String slug = SlugUtil.slugging(request.getName());
		final boolean exists = categoryRepository.existsBySlug(slug);
		if (exists) {
			throw new CategoryAlreadyExistException(request.getName());
		}

		if (request.getParentId() != null && !categoryRepository.existsById(request.getParentId())) {
			throw new CategoryParentNotFoundException(request.getParentId());
		}
		List<CategoryEntity> categories = categoryRepository.findAllByParentId(request.getParentId()).stream().toList();

		for (CategoryEntity categoryEntity : categories) {
			if (categoryEntity.getOrderNumber() >= request.getOrderNumber()) {
				categoryEntity.increaseOrder();
			}
		}

		CategoryEntity categoryEntity = categoryCreateRequestToCategoryEntityMapper.map(request);
		categoryEntity.setSlug(slug);
		categoryRepository.save(categoryEntity);
		categoryRepository.saveAll(categories);
	}


	@Override
	public void update(Long id, CategoryUpdateRequest request) {
		CategoryEntity categoryEntity = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		final boolean categoryExists = categoryRepository.findByName(request.getName())
			.filter(existingCategory -> !existingCategory.getId().equals(id))
			.isPresent();
		if (categoryExists) {
			throw new CategoryAlreadyExistException(request.getName());
		}
		categoryEntity.setName(request.getName());

		if (request.getParentId() != null) {
			if (!categoryRepository.existsById(request.getParentId())) {
				throw new CategoryParentNotFoundException(request.getParentId());
			}
			categoryEntity.setParentId(request.getParentId());
		}

		categoryEntity.setDescription(request.getDescription());

		categoryRepository.save(categoryEntity);
	}

	@Override
	public void delete(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		boolean isCategoryParent = categoryRepository.existsByParentId(id);

		if (isCategoryParent) {
			throw new CategoryHasChildException(id);
		}

		categoryRepository.delete(categoryEntity);
	}
}
