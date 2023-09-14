package org.gelecekbilimde.scienceplatform.media.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.media.dto.request.MediaRequest;
import org.gelecekbilimde.scienceplatform.exception.ClientException;
import org.gelecekbilimde.scienceplatform.media.model.Media;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.media.enums.MediaType;
import org.gelecekbilimde.scienceplatform.media.repository.MediaRepository;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.beans.factory.annotation.Value;
import org.gelecekbilimde.scienceplatform.media.dto.request.MediaGroupRequest;
import org.gelecekbilimde.scienceplatform.media.model.MediaGroup;
import org.gelecekbilimde.scienceplatform.media.repository.MediaGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
@RequiredArgsConstructor
public class MediaService {

	@Value("${file.upload.media.path}")
	private String mediaUploadPath;

	@Value("${file.upload.media.size}")
	private Long mediaUploadSize;

	private final MediaGroupRepository mediaGroupRepository;
	private final MediaRepository mediaRepository;

	private final Identity identity;


	public MediaGroupRequest saveMediaGroup(MediaGroupRequest mediaGroupRequest) {
		var mediaGroup = MediaGroup
				.builder()
				.name(mediaGroupRequest.getName())
				.userId(identity.getUserId())
			;

		if (mediaGroupRequest.getParentId() != null){
			mediaGroup.parentId(mediaGroupRequest.getParentId());
		}

		var entity = mediaGroupRepository.save(mediaGroup.build());

		mediaGroupRequest.setGroupId(entity.getId().intValue());

		return mediaGroupRequest;
	}

	public MediaRequest saveMedia(MediaRequest mediaRequest) {
		MediaGroup mediaGroup = this.mediaGroupRepository.findById(mediaRequest.getGroupId()).orElseThrow(()->new ClientException("Klasör Bulunamadı"));

		Media media = Media.builder()
				.url(mediaRequest.getUrl())
				.type(mediaRequest.getType())
				.mediaType(mediaRequest.getMediaType())
				.title(mediaRequest.getTitle())
				.shared(mediaRequest.isShared())
				.mediaGroup(mediaGroup)
				.userId(identity.getUserId())
				.build()
				;

		this.mediaRepository.save(media);
		return mediaRequest;
	}

	@Transactional
	public List<Object> uploadMedia(Integer groupId, MediaContentType mediaType, List<MultipartFile> files) {


		List<Object> messages = new ArrayList<>();
		for (MultipartFile file : files) {
			Map<String, Object> messageItem = new HashMap<>();

			try {

				messageItem.put("file", file.getOriginalFilename());

				if (file.getSize() >= mediaUploadSize){
					throw new IOException("Dosya boyutu büyük en fazla 10MB olmalı");
				}


				String originalFilename = file.getOriginalFilename();
				String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
				String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

				String fileName = UUID.randomUUID().toString().replace("-", "") + '.' + ext;

				String path = mediaUploadPath + '/' + identity.getUserId() + "/" + mediaType.toString();
				String url = "/" + path + "/" + fileName;

				File directory = new File(path);
				if (!directory.exists()){
					directory.mkdirs();
				}

				Path filePath = Paths.get(path,fileName);

				Files.copy(file.getInputStream(), filePath);

				MediaRequest mediaRequest = new MediaRequest();
				mediaRequest.setType(mediaType);
				mediaRequest.setTitle(title);
				mediaRequest.setShared(false);
				mediaRequest.setGroupId(groupId);
				mediaRequest.setUrl(url);
				mediaRequest.setMediaType(this.getMediaType(ext));

				this.saveMedia(mediaRequest);

				messageItem.put("status", "success");
			} catch (IOException e) {
				messageItem.put("status", "error");
				messageItem.put("message", e.getMessage());
			}

			messages.add(messageItem);
		}

		return  messages;
	}

	// todo : hangi formatlarda alacağımızı netleştirelim..
	private MediaType getMediaType(String ext)
	{
		MediaType mediaType = switch (ext) {
            case "jpg", "jpeg", "svg", "img","png" -> MediaType.IMAGE;
            case "gif" -> MediaType.GIF;
            default -> MediaType.IMAGE;
        };

        return mediaType;
	}
}
