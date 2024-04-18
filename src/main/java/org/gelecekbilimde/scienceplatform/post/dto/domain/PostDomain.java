package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.user.dto.domain.UserDomain;
import org.gelecekbilimde.scienceplatform.post.model.Category;

import java.util.List;


@Builder
@Getter
@Setter
public class PostDomain {


	private String postId;
	private String header;
	private String slug;
	private String content;
	private Category category;
	private Process lastProcess;
	private List<String> label;
	private Integer likeCount;
	private boolean active;
	private boolean copyrightControl;
	private boolean typoControl;
	private boolean dangerousControl;

	private String userId;

	private UserDomain user;
	private List<PostMediaDomain> medias;

}
