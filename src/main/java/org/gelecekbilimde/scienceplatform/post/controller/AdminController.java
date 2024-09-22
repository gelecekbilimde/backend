package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.Paging;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.post.model.Post;
import org.gelecekbilimde.scienceplatform.post.model.mapper.PostToAdminPostResponseMapper;
import org.gelecekbilimde.scienceplatform.post.model.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.model.request.PostManagerControlRequest;
import org.gelecekbilimde.scienceplatform.post.model.response.AdminPostResponse;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/post")
@PreAuthorize("hasAuthority('admin:access')")
class AdminController {

	private final PostService postService;
	private final PostProcessService postProcessService;

	private final PostToAdminPostResponseMapper postToAdminPostResponseMapper = PostToAdminPostResponseMapper.initialize();

	@GetMapping
	SuccessResponse<PagingResponse<AdminPostResponse>> getPostList(@Valid AdminPostListRequest request) {

		final Paging<Post> postList = postService.getPostListAdmin(request);

		final PagingResponse<AdminPostResponse> pageOfAdminUsersResponse = PagingResponse.<AdminPostResponse>builder()
			.of(postList)
			.content(postToAdminPostResponseMapper.map(postList.getContent()))
			.build();
		return SuccessResponse.success(pageOfAdminUsersResponse);
	}

	@GetMapping("/{postId}")
	SuccessResponse<Void> getPost(@Valid AdminPostListRequest request, @PathVariable Long postId) {
		return SuccessResponse.success();
	}


	@PutMapping("/{postId}")
	@PreAuthorize("hasAnyAuthority('admin:control','admin:last:control','post:create')")
	SuccessResponse<Void> updatePostProcess(@RequestBody @Valid PostManagerControlRequest request, @PathVariable Long postId) {
		postProcessService.updatePostProcess(request);
		return SuccessResponse.success();
	}

}
