package org.gelecekbilimde.scienceplatform.postprocess.dto.domain;

import lombok.Data;
import org.gelecekbilimde.scienceplatform.postprocess.enums.PostProcessEnum;

import java.time.LocalDateTime;


@Data

public class PostProcessDomain {


	private String header;
	private String slug;
	private String content;

	private PostProcessEnum lastProcess;
	private String message;
	private LocalDateTime createdDate;

}
