package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;

import java.util.List;

public interface CategoryService {

	BasePage<Category> findAll(CategoryListRequest listRequest);

	List<Category> findAll();

	Category findById(Long id);

	void create(CategoryCreateRequest request);

	void update(Long id, CategoryUpdateRequest request);

	void delete(Long id);

}



