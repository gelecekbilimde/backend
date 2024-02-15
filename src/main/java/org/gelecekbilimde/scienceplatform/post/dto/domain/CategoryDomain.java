package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDomain {

	private Long id;
	private String name;
	private Integer order;
	private String slug;
	private String icon;

	private Long parentId;
}
