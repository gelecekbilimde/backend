package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateRequest {

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank
	@Size(min = 2, max = 255)
	private String description;

	private Long parentId;

}
