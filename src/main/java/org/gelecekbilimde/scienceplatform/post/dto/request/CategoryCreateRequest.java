package org.gelecekbilimde.scienceplatform.post.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateRequest {
	@NotNull
	private String name;
	private Integer order;
	private String slug;
	private String icon;
	private Long parentId;
}
