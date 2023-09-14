package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Data;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;

import java.time.LocalDateTime;


@Data

public class PostProcessDomain {


	private String header;
	private String slug;
	private String content;

	private PostProcessEnum lastProcess;
	private String messages;
	private LocalDateTime createdDate;

}
