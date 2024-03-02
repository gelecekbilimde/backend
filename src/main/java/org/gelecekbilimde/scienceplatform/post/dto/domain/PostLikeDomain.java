package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostLikeDomain {

	private Integer likeCount;
	private LocalDateTime likedAt;

}
