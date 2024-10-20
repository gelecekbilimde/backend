package org.gelecekbilimde.scienceplatform.post.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.entity.CategoryEntity;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryToEntityMapper;
import org.gelecekbilimde.scienceplatform.post.port.CategoryDeletePort;
import org.gelecekbilimde.scienceplatform.post.port.CategoryReadPort;
import org.gelecekbilimde.scienceplatform.post.port.CategorySavePort;
import org.gelecekbilimde.scienceplatform.post.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class CategoryAdapter implements CategoryReadPort, CategorySavePort, CategoryDeletePort {

	private final CategoryRepository categoryRepository;


	private final CategoryEntityToDomainMapper categoryEntityToDomainMapper = CategoryEntityToDomainMapper.initialize();
	private final CategoryToEntityMapper categoryToEntityMapper = CategoryToEntityMapper.initialize();


	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll().stream()
			.map(categoryEntityToDomainMapper::map)
			.toList();
	}

	@Override
	public BasePage<Category> findAll(final BasePageable basePageable) {

		final Pageable pageable = basePageable.toPageable();

		final Page<CategoryEntity> categoriesPage = categoryRepository
			.findAll(Specification.allOf(), pageable);

		final List<Category> categories = categoryEntityToDomainMapper
			.map(categoriesPage.getContent());

		return BasePage.of(
			categoriesPage,
			categories
		);
	}


	@Override
	public List<Category> findAllByParentId(final Long parentId) {
		return categoryRepository.findAllByParentId(parentId).stream()
			.map(categoryEntityToDomainMapper::map)
			.toList();
	}


	@Override
	public Optional<Category> findById(final Long id) {
		return categoryRepository.findById(id)
			.map(categoryEntityToDomainMapper::map);
	}


	@Override
	public Optional<Category> findBySlug(final String slug) {
		return categoryRepository.findBySlug(slug)
			.map(categoryEntityToDomainMapper::map);
	}


	@Override
	public boolean notExistsById(final Long id) {
		return !categoryRepository.existsById(id);
	}


	@Override
	public boolean existsByParentId(Long parentId) {
		return categoryRepository.existsByParentId(parentId);
	}


	@Override
	public boolean existsBySlug(final String slug) {
		return categoryRepository.existsBySlug(slug);
	}


	@Override
	public void save(final Category category) {
		final CategoryEntity categoryEntity = categoryToEntityMapper.map(category);
		categoryRepository.save(categoryEntity);
	}


	@Override
	public void saveAll(final List<Category> categories) {
		final List<CategoryEntity> categoryEntities = categoryToEntityMapper.map(categories);
		categoryRepository.saveAll(categoryEntities);
	}


	@Override
	public void delete(final Category category) {
		final CategoryEntity categoryEntity = categoryToEntityMapper.map(category);
		categoryRepository.delete(categoryEntity);
	}

}
