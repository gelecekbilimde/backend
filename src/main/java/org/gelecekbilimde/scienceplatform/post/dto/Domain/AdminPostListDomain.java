package org.gelecekbilimde.scienceplatform.post.dto.Domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.postProcess.enums.PostProcessEnum;

import java.util.List;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class AdminPostListDomain {


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

}
