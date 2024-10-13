package org.gelecekbilimde.scienceplatform.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.media.util.SlugUtil;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryAlreadyExistException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryHasChildException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryParentNotFoundException;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryCreateRequestToDomainMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;
import org.gelecekbilimde.scienceplatform.post.port.CategoryDeletePort;
import org.gelecekbilimde.scienceplatform.post.port.CategoryReadPort;
import org.gelecekbilimde.scienceplatform.post.port.CategorySavePort;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CategoryServiceImpl implements CategoryService {

	private final CategoryReadPort categoryReadPort;
	private final CategorySavePort categorySavePort;
	private final CategoryDeletePort categoryDeletePort;


	private final CategoryCreateRequestToDomainMapper categoryCreateRequestToDomainMapper = CategoryCreateRequestToDomainMapper.initialize();


	@Override
	public BasePage<Category> findAll(final CategoryListRequest listRequest) {
		return categoryReadPort.findAll(listRequest.getPageable());
	}


	@Override
	public List<Category> findAll() {
		return categoryReadPort.findAll();
	}


	@Override
	public Category findById(Long id) {
		return categoryReadPort.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));
	}


	@Override
	public void create(CategoryCreateRequest createRequest) {

		final String slug = SlugUtil.slugging(createRequest.getName());
		final boolean exists = categoryReadPort.existsBySlug(slug);
		if (exists) {
			throw new CategoryAlreadyExistException(createRequest.getName());
		}

		if (createRequest.getParentId() != null && categoryReadPort.notExistsById(createRequest.getParentId())) {
			throw new CategoryParentNotFoundException(createRequest.getParentId());
		}

		final List<Category> reorderedSubCategories = this.reorderSubCategories(
			createRequest.getParentId(),
			createRequest.getOrderNumber()
		);

		final Category category = categoryCreateRequestToDomainMapper.map(createRequest);
		category.setSlug(slug);

		categorySavePort.save(category);
		categorySavePort.saveAll(reorderedSubCategories);
	}

	private List<Category> reorderSubCategories(final Long parentId, final Integer orderNumber) {
		final List<Category> categories = categoryReadPort.findAllByParentId(parentId);
		for (Category category : categories) {
			if (category.getOrderNumber() >= orderNumber) {
				category.increaseOrderNumber();
			}
		}
		return categories;
	}


	@Override
	public void update(final Long id, final CategoryUpdateRequest updateRequest) {

		final Category category = categoryReadPort.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		final String slug = SlugUtil.slugging(updateRequest.getName());
		final boolean exists = categoryReadPort.findBySlug(slug)
			.filter(existingCategory -> !existingCategory.getId().equals(id))
			.isPresent();
		if (exists) {
			throw new CategoryAlreadyExistException(updateRequest.getName());
		}

		if (updateRequest.getParentId() != null && categoryReadPort.notExistsById(updateRequest.getParentId())) {
			throw new CategoryParentNotFoundException(updateRequest.getParentId());
		}

		category.setName(updateRequest.getName());
		category.setSlug(slug);
		category.setParentId(updateRequest.getParentId());
		category.setDescription(updateRequest.getDescription());

		categorySavePort.save(category);
	}


	@Override
	public void delete(Long id) {

		Category category = categoryReadPort.findById(id)
			.orElseThrow(() -> new CategoryNotFoundException(id));

		boolean isCategoryParent = categoryReadPort.existsByParentId(id);
		if (isCategoryParent) {
			throw new CategoryHasChildException(id);
		}

		categoryDeletePort.delete(category);
	}
}
