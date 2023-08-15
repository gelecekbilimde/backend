package org.gelecekbilimde.scienceplatform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.Mapper.BusinessToResponseMapper;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.dto.Post.Business.AdminPostListBusinessDTO;
import org.gelecekbilimde.scienceplatform.dto.Post.Request.AdminPostListRequestDTO;
import org.gelecekbilimde.scienceplatform.dto.Post.Response.AdminPostListResponseDTO;
import org.gelecekbilimde.scienceplatform.service.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('admin:access')")
class AdminController {

	private final PostService postService;
	private static final BusinessToResponseMapper businessToResponseMapper = BusinessToResponseMapper.initialize();

	@RequestMapping("/admin/post")
	public Response<PagingResponse<AdminPostListResponseDTO>> getPost(HttpServletRequest httpServletRequest, @ModelAttribute @Valid AdminPostListRequestDTO request) {

		final Paging<AdminPostListBusinessDTO> postList = postService.getPostListForAdmin(request);

		final PagingResponse<AdminPostListResponseDTO> pageOfAdminUsersResponse = PagingResponse.<AdminPostListResponseDTO>builder()
			.of(postList)
			.content(businessToResponseMapper.map(postList.getContent()))
			.build();
		return Response.ok(pageOfAdminUsersResponse,httpServletRequest);
	}
}

