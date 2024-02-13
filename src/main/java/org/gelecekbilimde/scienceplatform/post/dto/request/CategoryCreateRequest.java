package org.gelecekbilimde.scienceplatform.post.dto.request;

import lombok.*;

@Getter
@Setter
public class CategoryCreateRequest {
	private String name;
	private Integer order;
	private String slug;
	private String icon;
	private Long parentId;
}
