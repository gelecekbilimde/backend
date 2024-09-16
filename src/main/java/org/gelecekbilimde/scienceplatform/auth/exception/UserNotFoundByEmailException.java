package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class UserNotFoundByEmailException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -6144535232442524262L;

	public UserNotFoundByEmailException(String email) {
		super("user does not found! email: " + email);
	}

}
