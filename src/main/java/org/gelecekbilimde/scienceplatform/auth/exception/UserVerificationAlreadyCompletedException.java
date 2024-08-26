package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class UserVerificationAlreadyCompletedException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 3624833133483334337L;

	public UserVerificationAlreadyCompletedException(String message) {
		super(message);
	}
}
