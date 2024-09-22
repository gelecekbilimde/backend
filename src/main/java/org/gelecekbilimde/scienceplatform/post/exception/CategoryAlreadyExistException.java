package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class CategoryAlreadyExistException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = 4587110803693357853L;

	public CategoryAlreadyExistException(String categoryName) {
		super("Category already exists with name: " + categoryName);
	}

}
