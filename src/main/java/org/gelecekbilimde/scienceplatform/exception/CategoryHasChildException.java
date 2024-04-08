package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CategoryHasChildException extends NotAllowedException{
	private Long categoryId;

	public CategoryHasChildException(Long categoryId) {
		super("Category with id " + categoryId + " has child categories. You cannot delete a category that has child categories.");
		this.categoryId = categoryId;
	}
}
