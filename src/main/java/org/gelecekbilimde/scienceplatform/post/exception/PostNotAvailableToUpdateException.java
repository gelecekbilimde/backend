package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class PostNotAvailableToUpdateException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = 5938421765385409304L;

	public PostNotAvailableToUpdateException(String id) {
		super("post not available to update! id: " + id);
	}

}
