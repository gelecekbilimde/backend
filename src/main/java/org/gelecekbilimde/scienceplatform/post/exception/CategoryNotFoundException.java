package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.NotFoundException;

import java.io.Serial;

public final class CategoryNotFoundException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = 2020894492289675714L;

	public CategoryNotFoundException(Long categoryId) {
		super("Category not found with id: " + categoryId);
	}

}
