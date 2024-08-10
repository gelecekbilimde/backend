package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private String header;

	private String slug;


	@NotNull(message = "cannot be null")
	private String content;

	@NotNull
	private CategoryCreateRequest category;

	private Process lastProcess;

	private List<String> label;

	private Integer likeCount = 0;
	private boolean active = false;

	private boolean copyrightControl = false;
	private boolean typoControl = false;
	private boolean dangerousControl = false;

	private List<PostMediaCreateRequest> medias;

}
