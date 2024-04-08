package org.gelecekbilimde.scienceplatform.post.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.CategoryResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryDomainToCategoryResponseMapper;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;
	private static final CategoryDomainToCategoryResponseMapper categoryDomainToCategoryResponseMapper = CategoryDomainToCategoryResponseMapper.initialize();

	@GetMapping("")
	public Response<List<CategoryResponse>> getCategoryList() {
		return Response.ok(categoryDomainToCategoryResponseMapper.map(categoryService.getCategories()));
	}

	@PostMapping("/create")
	public Response<Void> createCategory(@RequestBody CategoryCreateRequest request) {
		categoryService.createCategory(request);
		return Response.NO_CONTENT;
	}
}
