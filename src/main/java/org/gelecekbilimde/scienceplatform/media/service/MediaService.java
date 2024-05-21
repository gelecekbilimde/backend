package org.gelecekbilimde.scienceplatform.media.service;

import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.model.request.MediaGroupRequest;
import org.gelecekbilimde.scienceplatform.media.model.request.MediaRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

	MediaGroupRequest saveMediaGroup(MediaGroupRequest mediaGroupRequest);

	MediaRequest saveMedia(MediaRequest mediaRequest); // TODO : MediaRequest dönmek yerine void olabilir çünkü kullanılmıyor

	List<Object> uploadMedia(Long groupId, MediaContentType mediaType, List<MultipartFile> files);

}
