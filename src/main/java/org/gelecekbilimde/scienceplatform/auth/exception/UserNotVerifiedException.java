package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractAuthException;

import java.io.Serial;

public final class UserNotVerifiedException extends AbstractAuthException {

	@Serial
	private static final long serialVersionUID = -8766975383392137276L;

	public UserNotVerifiedException(String email) {
		super("user not verified yet! email: " + email);
	}

}
