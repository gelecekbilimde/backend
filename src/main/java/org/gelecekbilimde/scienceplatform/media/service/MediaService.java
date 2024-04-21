package org.gelecekbilimde.scienceplatform.media.service;

import org.gelecekbilimde.scienceplatform.media.dto.request.MediaGroupRequest;
import org.gelecekbilimde.scienceplatform.media.dto.request.MediaRequest;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
	public MediaGroupRequest saveMediaGroup(MediaGroupRequest mediaGroupRequest);

	public MediaRequest saveMedia(MediaRequest mediaRequest);

	public List<Object> uploadMedia(Integer groupId, MediaContentType mediaType, List<MultipartFile> files);

}
