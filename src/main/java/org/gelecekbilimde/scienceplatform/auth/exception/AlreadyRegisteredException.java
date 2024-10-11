package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public final class AlreadyRegisteredException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1256281437287259361L;

	public AlreadyRegisteredException(String email) {
		super("user already registered by " + email);
	}

}
