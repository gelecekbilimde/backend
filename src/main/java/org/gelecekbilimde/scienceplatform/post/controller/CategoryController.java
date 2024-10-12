package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.post.model.Category;
import org.gelecekbilimde.scienceplatform.post.model.mapper.CategoryToResponseMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.CategoryUpdateRequest;
import org.gelecekbilimde.scienceplatform.post.model.response.CategoryResponse;
import org.gelecekbilimde.scienceplatform.post.model.response.CategorySummaryResponse;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class CategoryController {

	private final CategoryService categoryService;
	private final CategoryToResponseMapper categoryToResponseMapper = CategoryToResponseMapper.initialize();


	@PostMapping("/categories")
	@PreAuthorize("hasAuthority('category:list')")
	SuccessResponse<List<CategoryResponse>> findAll() {
		List<Category> categories = categoryService.findAll();
		List<CategoryResponse> categoryResponses = categoryToResponseMapper.map(categories);
		return SuccessResponse.success(categoryResponses);
	}

	@GetMapping("/categories/summary")
	SuccessResponse<List<CategorySummaryResponse>> findAllSummary() {
		List<CategorySummaryResponse> summaryResponses = categoryService.findAllSummary();
		return SuccessResponse.success(summaryResponses);
	}

	@GetMapping("/category/{id}")
	@PreAuthorize("hasAuthority('category:detail')")
	SuccessResponse<CategoryResponse> findById(@PathVariable @Positive Long id) {
		Category category = categoryService.findById(id);
		CategoryResponse categoryResponse = categoryToResponseMapper.map(category);
		return SuccessResponse.success(categoryResponse);
	}

	@PostMapping("/category")
	@PreAuthorize("hasAuthority('category:create')")
	SuccessResponse<Void> create(@RequestBody @Valid CategoryCreateRequest request) {
		categoryService.create(request);
		return SuccessResponse.success();
	}

	@PutMapping("/category/{id}")
	@PreAuthorize("hasAuthority('category:update')")
	SuccessResponse<Void> update(@PathVariable @Positive Long id, @RequestBody @Valid CategoryUpdateRequest request) {
		categoryService.update(id, request);
		return SuccessResponse.success();
	}

	@DeleteMapping("/category/{id}")
	@PreAuthorize("hasAuthority('category:delete')")
	SuccessResponse<Void> delete(@PathVariable @Positive Long id) {
		categoryService.delete(id);
		return SuccessResponse.success();
	}

}
