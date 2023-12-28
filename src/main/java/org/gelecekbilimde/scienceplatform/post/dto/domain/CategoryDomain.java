package org.gelecekbilimde.scienceplatform.post.dto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CategoryDomain {

	private Long id;
	private String name;
	private CategoryDomain parent;
	private List<CategoryDomain> children;

//	private Integer postCount;
//	private Integer childCount;
//	private Integer overallPostCount;
//	private Integer overallChildCount;

//	private List<Category> parents;
//	private List<Category> overallChildren;
}
