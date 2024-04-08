package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CategoryAlreadyExistException extends NotAllowedException{
	private String categoryName;

	public CategoryAlreadyExistException(String categoryName) {
		super("Category already exists with name: " + categoryName);
		this.categoryName = categoryName;
	}
}
