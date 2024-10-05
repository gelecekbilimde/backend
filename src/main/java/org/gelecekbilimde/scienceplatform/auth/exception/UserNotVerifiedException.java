package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractAuthException;

import java.io.Serial;

public class UserNotVerifiedException extends AbstractAuthException {

	@Serial
	private static final long serialVersionUID = 263502105581485335L;

	public UserNotVerifiedException(String email) {
		super("user not verified yet! email: " + email);
	}

}
