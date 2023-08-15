package org.gelecekbilimde.scienceplatform.dto.Post.Business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.model.enums.PostProcessEnum;

import java.util.List;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class AdminPostListBusinessDTO {


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
