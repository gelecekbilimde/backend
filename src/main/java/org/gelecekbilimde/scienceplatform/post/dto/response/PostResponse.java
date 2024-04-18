package org.gelecekbilimde.scienceplatform.post.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.post.model.Category;

import java.util.List;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class PostResponse {


	private String header;
	private String slug;
	private String content;
	private Category category;
	private Process lastProcess;
	private List<String> label;
	private Integer likeCount ;
	private boolean active;
	private boolean copyrightControl;
	private boolean typoControl;
	private boolean dangerousControl;

	private PostMediaResponse media;

}
