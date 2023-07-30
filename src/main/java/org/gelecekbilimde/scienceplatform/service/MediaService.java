package org.gelecekbilimde.scienceplatform.service;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.dto.MediaDTO;
import org.gelecekbilimde.scienceplatform.model.Media;
import org.gelecekbilimde.scienceplatform.model.enums.MediaContentType;
import org.gelecekbilimde.scienceplatform.model.enums.MediaType;
import org.gelecekbilimde.scienceplatform.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.gelecekbilimde.scienceplatform.dto.MediaGroupDTO;
import org.gelecekbilimde.scienceplatform.model.MediaGroup;
import org.gelecekbilimde.scienceplatform.model.User;
import org.gelecekbilimde.scienceplatform.repository.MediaGroupRepository;
import org.gelecekbilimde.scienceplatform.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	@Autowired
	public MediaService(ApplicationContext applicationContext, PostRepository postRepository, MediaGroupRepository mediaGroupRepository, MediaRepository mediaRepository) {
		this.mediaGroupRepository = mediaGroupRepository;
		this.mediaRepository = mediaRepository;
	}


	public MediaGroupDTO saveMediaGroup(MediaGroupDTO mediaGroupDTO, User user) {
		var mediaGroup = MediaGroup
				.builder()
				.name(mediaGroupDTO.getName())
				.user(user)
			;

		if (mediaGroupDTO.getParentId() != null){
			mediaGroup.parentId(mediaGroupDTO.getParentId());
		}

		var entity = mediaGroupRepository.save(mediaGroup.build());

		mediaGroupDTO.setGroupId(entity.getId().intValue());

		return  mediaGroupDTO;
	}

	public MediaDTO saveMedia(MediaDTO mediaDTO, User user) {
		MediaGroup mediaGroup = this.mediaGroupRepository.findById(mediaDTO.getGroupId()).orElseThrow();

		var media = Media.builder()
				.url(mediaDTO.getUrl())
				.type(mediaDTO.getType())
				.mediaType(mediaDTO.getMediaType())
				.title(mediaDTO.getTitle())
				.shared(mediaDTO.getShared())
				.mediaGroup(mediaGroup)
				.user(user)
				.build()
				;

		this.mediaRepository.save(media);
		return  mediaDTO;
	}

	@Transactional
	public List<Object> uploadMedia(Integer groupId, MediaContentType mediaType, List<MultipartFile> files, User user) {


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

				String path = mediaUploadPath + '/' + user.getId() + "/" + mediaType.toString();
				String url = "/" + path + "/" + fileName;

				File directory = new File(path);
				if (!directory.exists()){
					directory.mkdirs();
				}

				Path filePath = Paths.get(path,fileName);

				Files.copy(file.getInputStream(), filePath);

				MediaDTO mediaDTO = new MediaDTO();
				mediaDTO.setType(mediaType);
				mediaDTO.setTitle(title);
				mediaDTO.setShared(false);
				mediaDTO.setGroupId(groupId);
				mediaDTO.setUrl(url);
				mediaDTO.setMediaType(this.getMediaType(ext));

				this.saveMedia(mediaDTO,user);

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
