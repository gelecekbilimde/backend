package org.gelecekbilimde.scienceplatform.postProcess.dto.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.comment.dto.Domain.response.CommentResponse;
import org.gelecekbilimde.scienceplatform.postMedia.dto.Response.PostMediaResponse;
import org.gelecekbilimde.scienceplatform.postProcess.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.user.dto.Response.UserResponse;

import java.util.Date;
import java.util.List;


@Data

public class PostProcessDomain {


	private String header;
	private String slug;
	private String content;

	private PostProcessEnum lastProcess;
	private String message;
	private Date createdDate;

}
