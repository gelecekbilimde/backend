package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Data;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;

import java.time.LocalDateTime;


@Data

public class PostProcessCreate {


	private Long postId;
	private String header;
	private String slug;
	private String content;

	private PostProcessEnum process;
	private String message;
	private LocalDateTime createdDate;

}
