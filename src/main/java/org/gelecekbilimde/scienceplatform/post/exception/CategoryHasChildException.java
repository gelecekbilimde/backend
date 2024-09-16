package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class CategoryHasChildException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -1220195011550284204L;

	public CategoryHasChildException(Long categoryId) {
		super("Category with id " + categoryId + " has child categories. You cannot delete a category that has child categories.");
	}

}
