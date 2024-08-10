package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryCreateRequest {
	@NotBlank
	private String name;
	private Integer order;
	private String slug;
	private String icon;
	private Long parentId;
}
