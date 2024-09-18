package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.GenericResponse;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryToResponseMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.response.CategoryResponse;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
class CategoryController {

	private final CategoryService categoryService;

	private final CategoryToResponseMapper categoryToResponseMapper = CategoryToResponseMapper.initialize();

	@GetMapping
	GenericResponse<List<CategoryResponse>> getCategoryList() {
		List<Category> categories = categoryService.getCategories();
		List<CategoryResponse> categoryResponses = categoryToResponseMapper.map(categories);
		return GenericResponse.success(categoryResponses);
	}

	@PostMapping("/create")
	GenericResponse<Void> createCategory(@RequestBody @Valid CategoryCreateRequest request) {
		categoryService.createCategory(request);
		return GenericResponse.success();
	}

}
