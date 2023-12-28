package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostManagerControl;
import org.gelecekbilimde.scienceplatform.post.dto.response.AdminPostResponse;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.mapper.PostDomainToAdminPostResponseMapper;
import org.gelecekbilimde.scienceplatform.post.service.PostProcessService;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('admin:access')")
class AdminController {

	private final PostService postService;
	private final PostProcessService postProcessService;
	private static final PostDomainToAdminPostResponseMapper postDomainToAdminPostResponseMapper = PostDomainToAdminPostResponseMapper.initialize();

	@GetMapping()
	public Response<PagingResponse<AdminPostResponse>> getPostList(@Valid AdminPostListRequest request) {

		final Paging<PostDomain> postList = postService.getPostListAdmin(request);

		final PagingResponse<AdminPostResponse> pageOfAdminUsersResponse = PagingResponse.<AdminPostResponse>builder()
			.of(postList)
			.content(postDomainToAdminPostResponseMapper.map(postList.getContent()))
			.build();
		return Response.ok(pageOfAdminUsersResponse);
	}

	// @PathVariable Long postId eklendi.
	@GetMapping("/{postId}")
	public Response<Void> getPost(@Valid AdminPostListRequest request , @PathVariable Long postId) {
		return Response.NO_CONTENT;
	}


	@PutMapping("/{postId}")
	@PreAuthorize("hasAnyAuthority('admin:control','admin:last:control','post:create')")
	public Response<Void> updatePostProcess(@RequestBody @Valid PostManagerControl request, @PathVariable Long postId) {
		postProcessService.updatePostProcess(request);
		return Response.NO_CONTENT;
	}

}
