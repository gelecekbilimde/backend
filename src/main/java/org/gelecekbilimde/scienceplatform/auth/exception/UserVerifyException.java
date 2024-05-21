package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public final class UserVerifyException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3164550469687782163L;

	public UserVerifyException(String message) {
		super(message);
	}

}
