package org.gelecekbilimde.scienceplatform.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostDomain;
import org.gelecekbilimde.scienceplatform.post.dto.domain.PostLikeDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.PostCreateRequest;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostLikeResponse;
import org.gelecekbilimde.scienceplatform.post.dto.response.PostResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.PostDomainToPostResponseMapper;
import org.gelecekbilimde.scienceplatform.post.mapper.PostLikeDomainToLikeResponseMapper;
import org.gelecekbilimde.scienceplatform.post.service.PostLikeToggleService;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
class PostController {

	private final PostService postService;
	private final PostLikeToggleService postLikeToggleService;

	private static final PostDomainToPostResponseMapper POST_DOMAIN_TO_RESPONSE = PostDomainToPostResponseMapper.initialize();
	private static final PostLikeDomainToLikeResponseMapper POST_LIKE_DOMAIN_TO_LIKE_RESPONSE_MAPPER = PostLikeDomainToLikeResponseMapper.initialize();

	@PostMapping
	@PreAuthorize("hasAuthority('post:create')")
	public Response<PostResponse> savePost(@RequestBody @Valid PostCreateRequest request) {
		PostDomain postDomain = postService.save(request);
		return Response.create(POST_DOMAIN_TO_RESPONSE.map(postDomain));
	}

	@PutMapping("/{id}/like/toggle")
	@PreAuthorize("hasAuthority('post:create')")
	public Response<PostLikeResponse> toggleLikeOfPost(@PathVariable String id) {
		PostLikeDomain postLike = postLikeToggleService.toggleLikeOfPost(id);
		PostLikeResponse postLikeResponse = POST_LIKE_DOMAIN_TO_LIKE_RESPONSE_MAPPER.map(postLike);
		return Response.ok(postLikeResponse);
	}

}
