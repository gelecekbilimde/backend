package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.domain.CategoryDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminCategoryListRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.AdminCategoryResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryDomainToAdminCategoryResponseMapper;
import org.gelecekbilimde.scienceplatform.post.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
	private final CategoryService categoryService;
	private static final CategoryDomainToAdminCategoryResponseMapper categoryDomainToAdminCategoryResponseMapper = CategoryDomainToAdminCategoryResponseMapper.initialize();

	@GetMapping()
	public Response<PagingResponse<AdminCategoryResponse>> getCategoryList(@Valid AdminCategoryListRequest request) {

		final Paging<CategoryDomain> categoryList = categoryService.getCategoryListAdmin(request);

		final PagingResponse<AdminCategoryResponse> pageOfAdminUsersResponse = PagingResponse.<AdminCategoryResponse>builder()
			.of(categoryList)
			.content(categoryDomainToAdminCategoryResponseMapper.map(categoryList.getContent()))
			.build();
		return Response.ok(pageOfAdminUsersResponse);
	}

	@PutMapping("/{categoryId}")
	@PreAuthorize("hasAnyAuthority('admin:control','admin:last:control','category:create')")
	public Response<Void> updateCategory(@PathVariable Long categoryId, @RequestBody String newName) {
		categoryService.changeCategoryName(categoryId, newName);
		return Response.NO_CONTENT;
	}

	@GetMapping("/test")
	public Response<Void> test() {
		categoryService.findAll();
		return Response.NO_CONTENT;
	}
}
