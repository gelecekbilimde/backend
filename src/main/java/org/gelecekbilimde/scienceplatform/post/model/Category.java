package org.gelecekbilimde.scienceplatform.post.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

	private Long id;
	private String name;
	private Integer order;
	private String slug;
	private String icon;

	private Long parentId;

}
