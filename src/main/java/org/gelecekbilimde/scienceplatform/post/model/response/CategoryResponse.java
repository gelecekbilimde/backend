package org.gelecekbilimde.scienceplatform.post.model.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponse {
	private Long id;
	private String name;
	private String description;
	private Long orderNumber;
	private String slug;
	private String icon;
	private Long parentId;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
}
