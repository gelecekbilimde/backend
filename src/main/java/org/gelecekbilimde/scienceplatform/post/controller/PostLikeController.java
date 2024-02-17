package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostLikeRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostLikeResponse;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/postlike")
public class PostLikeController {
	private final PostService postService;

	@PutMapping()
	@PreAuthorize("hasAuthority('post:create')")
	public Response<PostLikeResponse> likePost(HttpServletRequest request, @RequestBody @Valid PostLikeRequest postLikeRequest) {
		return postService.likePost(request, postLikeRequest);
	}
}
