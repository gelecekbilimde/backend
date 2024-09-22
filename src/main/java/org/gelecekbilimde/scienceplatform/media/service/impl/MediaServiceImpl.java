package org.gelecekbilimde.scienceplatform.media.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.gelecekbilimde.scienceplatform.media.exception.MediaGroupNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.media.model.entity.MediaEntity;
import org.gelecekbilimde.scienceplatform.media.model.entity.MediaGroupEntity;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaType;
import org.gelecekbilimde.scienceplatform.media.model.request.MediaGroupRequest;
import org.gelecekbilimde.scienceplatform.media.model.request.MediaRequest;
import org.gelecekbilimde.scienceplatform.media.repository.MediaGroupRepository;
import org.gelecekbilimde.scienceplatform.media.repository.MediaRepository;
import org.gelecekbilimde.scienceplatform.media.service.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class MediaServiceImpl implements MediaService {

	@Value("${file.upload.media.path}")
	private String mediaUploadPath;

	@Value("${file.upload.media.size}")
	private Long mediaUploadSize;

	private final MediaGroupRepository mediaGroupRepository;
	private final MediaRepository mediaRepository;
	private final Identity identity;

	private static final String STATUS = "status";
	private static final String MESSAGE = "message";
	private static final String FILE = "file";

	@Override
	public MediaGroupRequest saveMediaGroup(MediaGroupRequest mediaGroupRequest) { // TODO : MediaGroupRequest dönmek yerine MediaGroup ya da void olabilir
		var mediaGroup = MediaGroupEntity
			.builder()
			.name(mediaGroupRequest.getName())
			.userId(identity.getUserId());

		if (mediaGroupRequest.getParentId() != null) {
			mediaGroup.parentId(mediaGroupRequest.getParentId());
		}

		MediaGroupEntity entity = mediaGroupRepository.save(mediaGroup.build());

		mediaGroupRequest.setGroupId(entity.getId().intValue());

		return mediaGroupRequest;
	}

	@Override
	public MediaRequest saveMedia(MediaRequest mediaRequest) {
		MediaGroupEntity mediaGroupEntity = this.mediaGroupRepository.findById(mediaRequest.getGroupId())
			.orElseThrow(() -> new MediaGroupNotFoundByIdException(mediaRequest.getGroupId()));

		MediaEntity mediaEntity = MediaEntity.builder()
			.url(mediaRequest.getUrl())
			.contentType(mediaRequest.getContentType())
			.mediaType(mediaRequest.getMediaType())
			.title(mediaRequest.getTitle())
			.shared(mediaRequest.isShared())
			.mediaGroupEntity(mediaGroupEntity)
			.userId(identity.getUserId())

			.build();

		this.mediaRepository.save(mediaEntity);
		return mediaRequest;
	}

	@Override
	@Transactional
	public List<Object> uploadMedia(Long groupId, MediaContentType mediaType, List<MultipartFile> files) {
		List<Object> messages = new ArrayList<>();
		for (MultipartFile file : files) {
			Map<String, Object> messageItem = new HashMap<>();
			messageItem.put(FILE, file.getOriginalFilename());
			try {
				String originalFilename = file.getOriginalFilename();

				if (originalFilename == null) {
					messageItem.put(STATUS, "error");
					messageItem.put(MESSAGE, "Filename is null");
					messages.add(messageItem);
					continue;
				}

				if (file.getSize() >= mediaUploadSize) {
					throw new IOException("File size is too large max size is " + mediaUploadSize / 1024 / 1024 + "MB");
				}

				String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
				String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

				String fileName = RandomUtil.generateUUID().replace("-", "") + '.' + ext;

				String path = mediaUploadPath + File.separator + identity.getUserId() + File.separator + mediaType.toString();
				String url = File.separator + path + File.separator + fileName;

				File directory = new File(path);
				if (!directory.exists()) {
					directory.mkdirs();
				}

				Path filePath = Paths.get(path, fileName);

				Files.copy(file.getInputStream(), filePath);

				MediaRequest mediaRequest = new MediaRequest();
				mediaRequest.setContentType(mediaType);
				mediaRequest.setTitle(title);
				mediaRequest.setShared(false);
				mediaRequest.setGroupId(groupId);
				mediaRequest.setUrl(url);
				mediaRequest.setMediaType(MediaType.valueOfExtension(ext));

				this.saveMedia(mediaRequest);

				messageItem.put(STATUS, "success");
			} catch (IOException e) {
				messageItem.put(STATUS, "error");
				messageItem.put(MESSAGE, e.getMessage());
			}

			messages.add(messageItem);
		}

		return messages;
	}

}
