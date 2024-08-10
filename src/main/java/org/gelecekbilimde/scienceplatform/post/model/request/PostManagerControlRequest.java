package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.gelecekbilimde.scienceplatform.post.model.enums.Process;

@Data
public class PostManagerControlRequest {

	@NotNull(message = "cannot be null")
	private String postId;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private boolean copyrightControl;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private boolean typoControl;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private boolean dangerousControl;

	@NotNull(message = "cannot be null")
	private Process process;

	private String content;

	@NotNull
	private CategoryCreateRequest category;

	private String header;

	private String message;
	private String slug;

}
