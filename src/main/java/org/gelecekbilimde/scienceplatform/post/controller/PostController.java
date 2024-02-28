package org.gelecekbilimde.scienceplatform.post.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostLikeResponse;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.PostDomainToPostResponseMapper;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
class PostController {

	private final PostService postService;

	private static final PostDomainToPostResponseMapper POST_DOMAIN_TO_RESPONSE = PostDomainToPostResponseMapper.initialize();

	@PostMapping
	@PreAuthorize("hasAuthority('post:create')")
	public Response<PostResponse> savePost(@RequestBody @Valid PostCreateRequest request) {
		PostDomain postDomain = postService.save(request);
		return Response.create(POST_DOMAIN_TO_RESPONSE.map(postDomain));
	}
	@PutMapping("/{id}/like")
	@PreAuthorize("hasAuthority('post:create')")
	public Response<PostLikeResponse> likePost(@PathVariable String id) {
		return postService.likePost(id);
	}
}
