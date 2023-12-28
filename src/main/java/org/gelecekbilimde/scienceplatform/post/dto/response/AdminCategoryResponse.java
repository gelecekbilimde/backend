package org.gelecekbilimde.scienceplatform.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.post.model.Category;

import java.util.Set;

@Getter
@Setter
@Builder
public class AdminCategoryResponse {
	private Long id;
	private String name;
	private Long parentId;
	private String parentName;
	private Integer postCount;
	private Integer childCount;
	private Integer overallPostCount;
	private Integer overallChildCount;

	private Set<Category> parents;
	private Set<Category> children;
	private Set<Category> overallChildren;
}
