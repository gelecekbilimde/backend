package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class CategoryNotFoundException extends NotFoundException {
	private Long categoryId;

	public CategoryNotFoundException(Long categoryId) {
		super("Category not found with id: " + categoryId);
		this.categoryId = categoryId;
	}
}
