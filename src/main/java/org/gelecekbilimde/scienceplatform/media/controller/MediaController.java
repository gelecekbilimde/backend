package org.gelecekbilimde.scienceplatform.media.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.service.MediaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
class MediaController {

	private final MediaService mediaService;

	@PostMapping
	@PreAuthorize("hasAuthority('post:create')")
	public Response<List<Object>> secretVersionRole(@RequestParam("files") List<MultipartFile> files,
													@RequestParam("groupId") Integer groupId,
													@RequestParam("contentType") MediaContentType contentType) {

		//todo size kontrol
		return Response.ok(mediaService.uploadMedia(groupId, contentType, files));
	}
}
