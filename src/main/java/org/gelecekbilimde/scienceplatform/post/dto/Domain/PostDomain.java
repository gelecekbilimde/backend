package org.gelecekbilimde.scienceplatform.post.dto.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.comment.dto.Domain.response.CommentResponse;
import org.gelecekbilimde.scienceplatform.postMedia.dto.Response.PostMediaResponse;
import org.gelecekbilimde.scienceplatform.postProcess.enums.PostProcessEnum;
import org.gelecekbilimde.scienceplatform.user.dto.Response.UserResponse;

import java.util.List;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class PostDomain {


	private Long postId;
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

	private Long userId;
	//private UserResponse user;
	//private CommentResponse comment;
	//private PostMediaResponse media;

}
