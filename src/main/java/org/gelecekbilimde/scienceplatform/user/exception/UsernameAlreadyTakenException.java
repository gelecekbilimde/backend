package org.gelecekbilimde.scienceplatform.user.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractServerException;

import java.io.Serial;

public final class UsernameAlreadyTakenException extends AbstractServerException {

	@Serial
	private static final long serialVersionUID = -3365493595680402581L;

	public UsernameAlreadyTakenException(String username) {
		super(("username is already taken" + username));
	}

}
