package org.gelecekbilimde.scienceplatform.exception;

public class CategoryAlreadyExistException extends NotAllowedException {
	private String categoryName;

	public CategoryAlreadyExistException(String categoryName) {
		super("Category already exists with name: " + categoryName);
		this.categoryName = categoryName;
	}
}
