package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.exception.CategoryAlreadyExist;
import org.gelecekbilimde.scienceplatform.exception.CategoryHasChild;
import org.gelecekbilimde.scienceplatform.exception.CategoryNotFoundException;
import org.gelecekbilimde.scienceplatform.post.dto.request.CategoryCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.CategoryResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.CategoryCreateRequestToCategoryModelMapper;
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
	private static final CategoryCreateRequestToCategoryModelMapper categoryCreateRequestToCategoryModel = CategoryCreateRequestToCategoryModelMapper.initialize();
//	@GetMapping("")
//	public Response<PagingResponse<CategoryResponse>> getCategoryList(@Valid CategoryListRequest request) {
//
//		final Paging<CategoryDomain> categoryList = categoryService.getCategoryList(request);
//
//		final PagingResponse<CategoryResponse> pageOfCategoryListResponse = PagingResponse.<CategoryResponse>builder()
//			.of(categoryList)
//			.content(categoryDomainToCategoryResponseMapper.map(categoryList.getContent()))
//			.build();
//
//		return Response.ok(pageOfCategoryListResponse);
//	}

	@GetMapping("")
	public Response<List<CategoryResponse>> getCategoryList() {
		return Response.ok(categoryDomainToCategoryResponseMapper.map(categoryService.getCategoryList()));
	}

	@GetMapping("/{categoryId}")
	public Response<CategoryResponse> getCategory(@PathVariable Long categoryId) {
		try {
			return Response.ok(categoryDomainToCategoryResponseMapper.map(categoryService.getCategory(categoryId)));
		} catch (CategoryNotFoundException e) {
			// TODO: 404 dönecek
			return ;
		}

	}

	@PostMapping("/create")
	public Response<Void> createCategory(@RequestBody CategoryCreateRequest request) {
		try {
			categoryService.createCategory(request);
			return Response.ok(); // 201 dönülecek
		} catch (CategoryAlreadyExist e) {
			return 				; // 40? dönecek
		} catch (CategoryNotFoundException e) {
			return 				; // Öyle bir parent yok 404 ya da 400 dönecek
		}
	}

	@PutMapping("/{categoryId}/change-name")
	public Response<Void> changeCategoryName(@PathVariable Long categoryId, @RequestParam String newName) {
		try {
			categoryService.changeCategoryName(categoryId, newName);
			return Response.ok(); // 204 dönecek
		} catch (CategoryNotFoundException e) {
			return 				;// 404 ya da 400 dönecek
		}
	}

	@DeleteMapping("/{categoryId}")
	public Response<Void> deleteCategory(@PathVariable Long categoryId) {
		try {
			categoryService.deleteCategory(categoryId);
			return Response.ok() 	; // 204 dönülecek
		} catch (CategoryNotFoundException e) {
			return 					; // 404 ya da 400 dönecek
		} catch (CategoryHasChild e) {
			return 					; // 400 dönecek
		}
	}
}
