package org.gelecekbilimde.scienceplatform.post.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class PostProcessNotFoundByPostIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -1210345437568866472L;

	public PostProcessNotFoundByPostIdException(String postId) {
		super("post process does not found! postId: " + postId);
	}

}
