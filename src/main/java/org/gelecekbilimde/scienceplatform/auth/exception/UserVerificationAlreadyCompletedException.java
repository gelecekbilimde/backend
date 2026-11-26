package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public final class UserVerificationAlreadyCompletedException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 5017223706763359158L;

	public UserVerificationAlreadyCompletedException() {
		super("User verification is already completed!");
	}

}
