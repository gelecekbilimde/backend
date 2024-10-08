package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class UserVerificationIsNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 7045861607490841147L;

	public UserVerificationIsNotFoundException(String id) {
		super("User Verification ID does not found! verificationId: " + id);
	}


}
