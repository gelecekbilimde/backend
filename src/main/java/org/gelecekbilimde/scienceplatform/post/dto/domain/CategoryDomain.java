package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CategoryDomain {

	private Long id;
	private Integer order;
	private String name;
	private String slug;
	private String icon;

	private CategoryDomain parent;
	private List<CategoryDomain> children;
}
