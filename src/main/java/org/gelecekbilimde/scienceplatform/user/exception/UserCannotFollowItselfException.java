package org.gelecekbilimde.scienceplatform.user.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public class UserCannotFollowItselfException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = 8300066406018173223L;

	public UserCannotFollowItselfException(String userId) {
		super("user cannot follow itself by userId:" + userId);
	}

}
