package org.gelecekbilimde.scienceplatform.post.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class CategoryListResponse {
	private Long id;
	private String name;
	private Long order;
	private String slug;
	private String icon;
	private Set<CategoryListResponse> children;
}
