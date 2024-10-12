package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank
	@Size(min = 2, max = 255)
	private String description;

	@NotNull
	@Positive
	private Integer orderNumber;

	@Size(max = 50)
	private String icon;

	@Positive
	private Long parentId;

}
