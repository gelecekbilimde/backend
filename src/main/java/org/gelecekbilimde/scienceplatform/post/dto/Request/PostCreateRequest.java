package org.gelecekbilimde.scienceplatform.post.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.comment.dto.Domain.response.CommentResponse;
import org.gelecekbilimde.scienceplatform.postMedia.dto.Request.PostMediaCreate;
import org.gelecekbilimde.scienceplatform.user.dto.Response.UserResponse;
import org.gelecekbilimde.scienceplatform.postProcess.enums.PostProcessEnum;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private String header;

	// todo bu var eyvallah ama ya iki tane aynı başlıkta post gelirse beki sonuna bir sayı eklemeliyiz
	private String slug;


	@NotNull(message = "cannot be null")
	private String content;

	private UserResponse user;

	private PostProcessEnum lastProcess;

	private List<String> label;

	private Integer likeCount = 0;
	private Boolean active = false;

	private Boolean copyrightControl = false;
	private Boolean typoControl = false;
	private Boolean dangerousControl = false;

	private List<PostMediaCreate> medias;

	private List<CommentResponse> comments;

}
