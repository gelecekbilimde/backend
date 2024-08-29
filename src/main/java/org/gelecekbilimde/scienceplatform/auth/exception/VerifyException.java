package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class VerifyException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3063053165076191449L;

	public VerifyException(String email) {
		super("user must do the verification process: " + email);
	}
}
