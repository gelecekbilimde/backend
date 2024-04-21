package org.gelecekbilimde.scienceplatform.media.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.media.dto.request.MediaGroupRequest;
import org.gelecekbilimde.scienceplatform.media.dto.request.MediaRequest;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.enums.MediaType;
import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.gelecekbilimde.scienceplatform.media.model.MediaGroup;
import org.gelecekbilimde.scienceplatform.media.repository.MediaGroupRepository;
import org.gelecekbilimde.scienceplatform.media.repository.MediaRepository;
import org.gelecekbilimde.scienceplatform.media.service.MediaService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
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
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

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
	public MediaGroupRequest saveMediaGroup(MediaGroupRequest mediaGroupRequest) {
		var mediaGroup = MediaGroup
			.builder()
			.name(mediaGroupRequest.getName())
			.userId(identity.getUserId());

		if (mediaGroupRequest.getParentId() != null) {
			mediaGroup.parentId(mediaGroupRequest.getParentId());
		}

		MediaGroup entity = mediaGroupRepository.save(mediaGroup.build());

		mediaGroupRequest.setGroupId(entity.getId().intValue());

		return mediaGroupRequest;
	}

	@Override
	public MediaRequest saveMedia(MediaRequest mediaRequest) {
		MediaGroup mediaGroup = this.mediaGroupRepository.findById(mediaRequest.getGroupId()).orElseThrow(() -> new ClientException("Klasör Bulunamadı"));

		Media media = Media.builder()
			.url(mediaRequest.getUrl())
			.contentType(mediaRequest.getContentType())
			.mediaType(mediaRequest.getMediaType())
			.title(mediaRequest.getTitle())
			.shared(mediaRequest.isShared())
			.mediaGroup(mediaGroup)
			.userId(identity.getUserId())

			.build();

		this.mediaRepository.save(media);
		return mediaRequest;
	}

	@Override
	@Transactional
	public List<Object> uploadMedia(Integer groupId, MediaContentType mediaType, List<MultipartFile> files) {
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

				String fileName = UUID.randomUUID().toString().replace("-", "") + '.' + ext;

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
