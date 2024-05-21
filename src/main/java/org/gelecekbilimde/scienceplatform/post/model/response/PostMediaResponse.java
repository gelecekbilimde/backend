package org.gelecekbilimde.scienceplatform.post.model.response;

import lombok.Builder;
import lombok.Getter;
import org.gelecekbilimde.scienceplatform.media.model.enums.MediaContentType;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostMediaResponse {

	private Long id;
	private Long mediaId;
	private boolean cover;
	private LocalDateTime createdDate;

	private String url;
	private MediaContentType contentType;
	private String title;
	private boolean shared;

	private Long groupId;
	private String groupName;
}
