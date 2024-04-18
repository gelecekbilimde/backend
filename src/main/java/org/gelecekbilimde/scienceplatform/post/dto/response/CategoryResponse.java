package org.gelecekbilimde.scienceplatform.post.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryResponse {
	private Long id;
	private String name;
	private Long order;
	private String slug;
	private String icon;

	private Long parentId;
}
