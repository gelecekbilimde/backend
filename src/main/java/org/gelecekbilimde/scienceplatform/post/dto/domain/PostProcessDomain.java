package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Data;


@Data

public class PostProcessDomain {

	private Long postId;
	private PostDomain first;
	private PostDomain last;
	private PostDomain changes;
	private boolean isChanged;
}
