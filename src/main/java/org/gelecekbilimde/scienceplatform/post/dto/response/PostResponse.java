package org.gelecekbilimde.scienceplatform.post.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.comment.dto.domain.response.CommentResponse;
import org.gelecekbilimde.scienceplatform.postmedia.dto.response.PostMediaResponse;
import org.gelecekbilimde.scienceplatform.postprocess.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.user.dto.response.UserResponse;

import java.util.List;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class PostResponse {


	private String header;
	private String slug;
	private String content;
	private PostProcessEnum lastProcess;
	private List<String> label;
	private Integer likeCount ;
	private Boolean active;
	private Boolean copyrightControl;
	private Boolean typoControl;
	private Boolean dangerousControl;

	private UserResponse user;
	private CommentResponse comment;
	private PostMediaResponse media;

}
