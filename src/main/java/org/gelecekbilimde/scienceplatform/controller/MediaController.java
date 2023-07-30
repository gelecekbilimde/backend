package org.gelecekbilimde.scienceplatform.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.ApiResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

	private final MediaService mediaService;
	@PostMapping("")
	@PreAuthorize("hasAuthority('post:create')")
	public ResponseEntity<ApiResponse> secretVersionRole(HttpServletRequest httpServletRequest,
														 @RequestParam("files") List<MultipartFile> files,
														 @RequestParam("groupId") Integer groupId,
														 @RequestParam("type") MediaContentType mediaType)
	{
		//todo size kontrol
		return Response.ok(httpServletRequest, mediaService.uploadMedia(groupId, mediaType, files, (User) httpServletRequest.getAttribute("user")));
	}
}
