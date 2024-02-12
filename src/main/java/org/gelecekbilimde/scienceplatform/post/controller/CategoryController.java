package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.CategoryListResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryDomainToCategoryListResponseMapper;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;
	private static final CategoryDomainToCategoryListResponseMapper categoryDomainToCategoryListResponseMapper = CategoryDomainToCategoryListResponseMapper.initialize();

	@GetMapping("")
	public Response<PagingResponse<CategoryListResponse>> getCategoryList(@Valid CategoryListRequest request) {

		final Paging<CategoryDomain> categoryList = categoryService.getCategoryList(request);

		final PagingResponse<CategoryListResponse> pageOfCategoryListResponse = PagingResponse.<CategoryListResponse>builder()
			.of(categoryList)
			.content(categoryDomainToCategoryListResponseMapper.map(categoryList.getContent()))
			.build();

		return Response.ok(pageOfCategoryListResponse);
	}

	@GetMapping("/{categoryId}")
	public Response<CategoryDomain> getCategory(@PathVariable Long categoryId) {
		return Response.ok(categoryService.getCategory(categoryId));
	}

	@PostMapping("/create")
	public Response<Void> createCategory(@RequestBody CategoryCreateRequest request) {
		categoryService.createCategory(request);
		return Response.NO_CONTENT;
	}
}
