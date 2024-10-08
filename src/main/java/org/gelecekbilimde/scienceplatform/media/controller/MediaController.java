package org.gelecekbilimde.scienceplatform.media.controller;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.service.MediaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
class MediaController {

	private final MediaService mediaService;

	@PostMapping
	@PreAuthorize("hasAuthority('post:create')")
	SuccessResponse<List<Object>> secretVersionRole(@RequestParam("files") List<MultipartFile> files,
													@RequestParam("groupId") Long groupId,
													@RequestParam("contentType") MediaContentType contentType) {

		//todo size kontrol
		return SuccessResponse.success(mediaService.uploadMedia(groupId, contentType, files));
	}

}
