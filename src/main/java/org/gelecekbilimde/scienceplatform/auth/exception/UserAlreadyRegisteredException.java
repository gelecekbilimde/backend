package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class UserAlreadyRegisteredException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -5670350199459862153L;

	public UserAlreadyRegisteredException(String email) {
		super("user already registered by " + email);
	}

}
