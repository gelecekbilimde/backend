package org.gelecekbilimde.scienceplatform.post.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class CategoryListResponse {
	private Long id;
	private Long order;
	private String name;
	private String slug;
	private String icon;
//	private String description;
	private Set<CategoryListResponse> children;
}
