package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Data;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;

import java.time.LocalDateTime;
import java.util.List;


@Data

public class PostProcessCreate {


	private Long postId;
	private String header;
	private String slug;
	private String content;

	private PostProcessEnum process;
	private List<PostProcessMessage> message;
	private LocalDateTime createdDate;

}
