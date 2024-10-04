package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryToResponseMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;
import org.gelecekbilimde.scienceplatform.post.model.response.CategoryResponse;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
class CategoryController {

	private final CategoryService categoryService;

	private final CategoryToResponseMapper categoryToResponseMapper = CategoryToResponseMapper.initialize();

	@GetMapping
	SuccessResponse<List<CategoryResponse>> getCategoryList() {
		List<Category> categories = categoryService.getCategories();
		List<CategoryResponse> categoryResponses = categoryToResponseMapper.map(categories);
		return SuccessResponse.success(categoryResponses);
	}

	@GetMapping("/{id}")
	SuccessResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
		Category category = categoryService.getCategory(id);
		CategoryResponse categoryResponse = categoryToResponseMapper.map(category);
		return SuccessResponse.success(categoryResponse);
	}

	@PostMapping("/create")
	SuccessResponse<Void> createCategory(@RequestBody @Valid CategoryCreateRequest request) {
		categoryService.createCategory(request);
		return SuccessResponse.success();
	}

	@PutMapping("/{id}/update")
	SuccessResponse<Void> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryUpdateRequest request) {
		categoryService.updateCategory(id, request);
		return SuccessResponse.success();
	}

	@DeleteMapping("/{id}/delete")
	SuccessResponse<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return SuccessResponse.success();
	}

}
