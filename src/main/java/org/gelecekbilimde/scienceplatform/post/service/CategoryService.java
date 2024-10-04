package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.exception.CategoryAlreadyExistException;
import org.gelecekbilimde.scienceplatform.post.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {

	Category getCategory(Long categoryId);

	List<Category> getCategories();

	void createCategory(CategoryCreateRequest request);

	void updateCategory(Long id, CategoryUpdateRequest request);

	void deleteCategory(Long categoryId);

}

/*
 * Creates a new category.
 * If there is a category with same order, it increases the order of the siblings (categories with the same parent).
 * @param request The request object that contains the name,order and if it has a parent, parentId of the category.
 * @throws CategoryAlreadyExistException throws if a category with the same name already exists.
 * @throws CategoryNotFoundException throws if there is no category with the given parentId.
 */


