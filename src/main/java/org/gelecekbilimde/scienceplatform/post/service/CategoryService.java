package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryListRequest;

public interface CategoryService {

	Paging<CategoryDomain> getCategoryList(CategoryListRequest request);

	CategoryDomain getCategory(Long categoryId);

	void createCategory(CategoryCreateRequest request);

	void changeCategoryName(Long categoryId, String newName);
}
