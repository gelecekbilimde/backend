package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.post.enums.PostProcessEnum;

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
	private boolean active;
	private boolean copyrightControl;
	private boolean typoControl;
	private boolean dangerousControl;

	private Long userId;

}
