package org.gelecekbilimde.scienceplatform.dto.Post.Response;

import lombok.Builder;
import lombok.Getter;

import org.gelecekbilimde.scienceplatform.model.enums.PostProcessEnum;

import java.util.List;


@Getter
@Builder
public class AdminPostListResponseDTO {


	private String header;
	private String slug;
	private String content;
	private PostProcessEnum lastProcess;
	private List<String> label;
	private Integer likeCount;
	private Boolean active;
	private Boolean copyrightControl;
	private Boolean typoControl;
	private Boolean dangerousControl;
}
