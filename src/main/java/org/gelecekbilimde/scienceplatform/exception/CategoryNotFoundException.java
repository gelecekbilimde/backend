package org.gelecekbilimde.scienceplatform.exception;

public class CategoryNotFoundException extends NotFoundException {
	private Long categoryId;

	public CategoryNotFoundException(Long categoryId) {
		super("Category not found with id: " + categoryId);
		this.categoryId = categoryId;
	}
}
