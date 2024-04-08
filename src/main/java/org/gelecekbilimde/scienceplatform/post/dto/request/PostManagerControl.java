package org.gelecekbilimde.scienceplatform.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.gelecekbilimde.scienceplatform.post.enums.Process;
import org.gelecekbilimde.scienceplatform.post.model.Category;


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

	@NotNull
	private Category category;

	private String header;

	private String message;
	private String slug;

}
