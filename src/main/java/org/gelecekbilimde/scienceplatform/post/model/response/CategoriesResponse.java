package org.gelecekbilimde.scienceplatform.post.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoriesResponse {

	private Long id;
	private Long parentId;
	private String name;
	private Long orderNumber;
	private String slug;
	private String icon;
	private String createdBy;
	private LocalDateTime createdAt;

}
