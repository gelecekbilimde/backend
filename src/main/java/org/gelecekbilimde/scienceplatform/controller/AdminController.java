package org.gelecekbilimde.scienceplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.mapper.DomainToResponseMapper;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.dto.post.Domain.AdminPostListDomainDTO;
import org.gelecekbilimde.scienceplatform.dto.post.Request.AdminPostListRequestDTO;
import org.gelecekbilimde.scienceplatform.dto.post.Response.AdminPostListResponseDTO;
import org.gelecekbilimde.scienceplatform.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('admin:access')")
class AdminController {

	private final PostService postService;
	private static final DomainToResponseMapper domainToResponseMapper = DomainToResponseMapper.initialize();

	@RequestMapping("/admin/post")
	public Response<PagingResponse<AdminPostListResponseDTO>> getPost(@ModelAttribute @Valid AdminPostListRequestDTO request) {

		final Paging<AdminPostListDomainDTO> postList = postService.getPostListForAdmin(request);

		final PagingResponse<AdminPostListResponseDTO> pageOfAdminUsersResponse = PagingResponse.<AdminPostListResponseDTO>builder()
			.of(postList)
			.content(domainToResponseMapper.map(postList.getContent()))
			.build();
		return Response.ok(pageOfAdminUsersResponse);
	}
}

