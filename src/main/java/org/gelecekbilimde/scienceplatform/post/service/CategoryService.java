package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;
import org.gelecekbilimde.scienceplatform.post.model.response.CategorySummaryResponse;

import java.util.List;

public interface CategoryService {

	List<Category> findAll();

	List<CategorySummaryResponse> findAllSummary();

	Category findById(Long id);

	void create(CategoryCreateRequest request);

	void update(Long id, CategoryUpdateRequest request);

	void delete(Long id);

}



