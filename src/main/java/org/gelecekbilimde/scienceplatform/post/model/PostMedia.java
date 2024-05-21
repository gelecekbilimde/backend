package org.gelecekbilimde.scienceplatform.post.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class PostMedia {

	private Long id;
	private Long mediaId;
	private boolean cover;

	private String url;
	private MediaContentType contentType;
	private String title;
	private boolean shared;

	private Long groupId;
	private String groupName;

	private LocalDateTime createdAt;

}
