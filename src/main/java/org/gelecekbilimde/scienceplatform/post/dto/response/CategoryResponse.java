package org.gelecekbilimde.scienceplatform.post.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.post.model.Category;

import java.util.Set;

@Data
@EqualsAndHashCode()
@SuperBuilder
public class CategoryResponse {

	private String name;
	private Set<Category> parents;

}
