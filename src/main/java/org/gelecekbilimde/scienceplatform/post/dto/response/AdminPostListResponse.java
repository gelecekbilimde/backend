package org.gelecekbilimde.scienceplatform.post.dto.response;

import lombok.Builder;
import lombok.Getter;

import org.gelecekbilimde.scienceplatform.user.dto.response.UserResponse;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;

import java.util.List;


@Getter
@Builder
public class AdminPostListResponse {


	private String header;
	private String slug;
	private String content;
	private PostProcessEnum lastProcess;
	private List<String> label;
	private Integer likeCount;
	private boolean active;
	private boolean copyrightControl;
	private boolean typoControl;
	private boolean dangerousControl;
	private UserResponse user;
	private PostMediaResponse media;
}
