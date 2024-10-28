package org.gelecekbilimde.scienceplatform.user.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class UsernameAlreadyTakenException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -3365493595680402581L;

	public UsernameAlreadyTakenException(String username) {
		super(String.format("'%s' username already taken", username));
	}

}
