package org.gelecekbilimde.scienceplatform.post.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
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
@RequestMapping("/category")
class CategoryController {

	private final CategoryService categoryService;

	private final CategoryToResponseMapper categoryToResponseMapper = CategoryToResponseMapper.initialize();

	@GetMapping
	public Response<List<CategoryResponse>> getCategoryList() {
		return Response.ok(categoryToResponseMapper.map(categoryService.getCategories()));
	}

	@PostMapping("/create")
	public Response<Void> createCategory(@RequestBody CategoryCreateRequest request) {
		categoryService.createCategory(request);
		return Response.NO_CONTENT;
	}
}
