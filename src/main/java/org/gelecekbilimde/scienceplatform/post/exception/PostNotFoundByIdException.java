package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.NotFoundException;

import java.io.Serial;

public final class PostNotFoundByIdException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = 4625081929118003389L;

	public PostNotFoundByIdException(String id) {
		super("post does not found! id: " + id);
	}

}
