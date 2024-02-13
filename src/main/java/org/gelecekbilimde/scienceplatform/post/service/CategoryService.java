package org.gelecekbilimde.scienceplatform.post.service;

import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import java.util.List;

public interface CategoryService {

//	Paging<CategoryDomain> getCategoryList(CategoryListRequest request);
//
//	CategoryDomain getCategory(Long categoryId);
//
//	void createCategory(CategoryCreateRequest request);
//
//	void changeCategoryName(Long categoryId, String newName);
//
//	Response<CategoryDeleteResponse> deleteCategory(Long categoryId);

	List<CategoryDomain> getCategoryList();

	CategoryDomain getCategory(Long categoryId);

	void createCategory(CategoryCreateRequest request);

	void changeCategoryName(Long categoryId, String newName);

	void deleteCategory(Long categoryId);
}
