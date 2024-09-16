package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class CategoryParentNotFoundException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = 6496865248758628215L;

	public CategoryParentNotFoundException(Long parentId) {
		super("Parent not found with id: " + parentId);
	}

}
