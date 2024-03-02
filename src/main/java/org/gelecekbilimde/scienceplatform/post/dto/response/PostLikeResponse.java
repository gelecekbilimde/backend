package org.gelecekbilimde.scienceplatform.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostLikeResponse {

	private Integer likeCount;
	private LocalDateTime likedAt;

}
