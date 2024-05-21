package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.NotFoundException;

import java.io.Serial;

public final class CategoryParentNotFoundException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = 6496865248758628215L;

	public CategoryParentNotFoundException(Long parentId) {
		super("Parent not found with id: " + parentId);
	}

}
