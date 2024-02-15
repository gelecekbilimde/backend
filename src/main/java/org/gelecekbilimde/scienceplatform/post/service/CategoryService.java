package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.exception.CategoryAlreadyExist;
import org.gelecekbilimde.scienceplatform.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;

import java.util.List;

public interface CategoryService {

	List<CategoryDomain> getCategoryList();

	CategoryDomain getCategory(Long categoryId);

	/**
	 * Creates a new category.
	 * If there is a category with same order, it increases the order of the siblings (categories with the same parent).
	 *
	 * @param request The request object that contains the name,order and if it has a parent, parentId of the category.
	 * @throws CategoryAlreadyExist      throws if a category with the same name already exists.
	 * @throws CategoryNotFoundException throws if there is no category with the given parentId.
	 */
	void createCategory(CategoryCreateRequest request);

	void changeCategoryName(Long categoryId, String newName);

	void deleteCategory(Long categoryId);
}
