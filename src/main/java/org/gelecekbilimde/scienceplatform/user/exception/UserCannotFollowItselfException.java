package org.gelecekbilimde.scienceplatform.user.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class UserCannotFollowItselfException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -1651758308998005837L;

	public UserCannotFollowItselfException(String userId) {
		super("user cannot follow itself by userId:" + userId);
	}

}
