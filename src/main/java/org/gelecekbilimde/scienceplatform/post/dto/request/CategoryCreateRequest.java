package org.gelecekbilimde.scienceplatform.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequest {
	private Integer order;
	private String name;
	private String slug;
	private String icon;
	private Long parentId;
}
