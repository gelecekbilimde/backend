package org.gelecekbilimde.scienceplatform.post.conroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.dto.response.AdminPostListResponse;
import org.gelecekbilimde.scienceplatform.post.mapper.AdminPostListDomainToResponseMapper;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.post.dto.domain.AdminPostListDomain;
import org.gelecekbilimde.scienceplatform.post.dto.request.AdminPostListRequest;
import org.gelecekbilimde.scienceplatform.post.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('admin:access')")
class AdminController {

	private final PostService postService;
	private static final AdminPostListDomainToResponseMapper domainToResponseMapper = AdminPostListDomainToResponseMapper.initialize();

	@RequestMapping("/admin/post")
	public Response<PagingResponse<AdminPostListResponse>> getPost(@ModelAttribute @Valid AdminPostListRequest request) {

		final Paging<AdminPostListDomain> postList = postService.getPostListForAdmin(request);

		final PagingResponse<AdminPostListResponse> pageOfAdminUsersResponse = PagingResponse.<AdminPostListResponse>builder()
			.of(postList)
			.content(domainToResponseMapper.map(postList.getContent()))
			.build();
		return Response.ok(pageOfAdminUsersResponse);
	}
}

