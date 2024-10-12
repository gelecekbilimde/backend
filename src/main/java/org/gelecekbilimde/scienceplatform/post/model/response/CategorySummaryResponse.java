package org.gelecekbilimde.scienceplatform.post.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySummaryResponse {
	private Long id;
	private String name;
	private Long orderNumber;
	private String slug;
	private String icon;
}
