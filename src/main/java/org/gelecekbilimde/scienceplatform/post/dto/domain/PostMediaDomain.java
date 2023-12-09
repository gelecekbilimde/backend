package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.media.enums.MediaContentType;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class PostMediaDomain {

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
