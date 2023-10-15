package org.gelecekbilimde.scienceplatform.media.conroller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.service.MediaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
class MediaController {

	private final MediaService mediaService;

	@PostMapping()
	@PreAuthorize("hasAuthority('post:create')")
	public Response<List<Object>> secretVersionRole(HttpServletRequest httpServletRequest,
													@RequestParam("files") List<MultipartFile> files,
													@RequestParam("groupId") Integer groupId,
													@RequestParam("contentType") MediaContentType contentType) {
		//todo size kontrol
		return Response.ok(mediaService.uploadMedia(groupId, contentType, files));
	}
}
