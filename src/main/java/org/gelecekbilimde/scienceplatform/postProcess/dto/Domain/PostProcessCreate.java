package org.gelecekbilimde.scienceplatform.postProcess.dto.Domain;

import lombok.Data;
import org.gelecekbilimde.scienceplatform.postProcess.enums.PostProcessEnum;

import java.util.Date;
import java.util.List;


@Data

public class PostProcessCreate {


	private Long postId;
	private String header;
	private String slug;
	private String content;

	private PostProcessEnum process;
	private List<Message> message;
	private Date createdDate;

}
