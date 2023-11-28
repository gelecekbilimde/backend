package org.gelecekbilimde.scienceplatform.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.gelecekbilimde.scienceplatform.post.enums.Process;


@Data
public class PostManagerControl {

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

	private String header;

	private String message;
	private String slug;

}
