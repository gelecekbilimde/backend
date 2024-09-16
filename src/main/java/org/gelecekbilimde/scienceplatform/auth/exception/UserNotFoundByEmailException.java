package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.NotFoundException;

import java.io.Serial;

public final class UserNotFoundByEmailException extends NotFoundException {

	@Serial
	private static final long serialVersionUID = 3275707458511858867L;

	public UserNotFoundByEmailException() {
		super("user does not found!");
	}

	public UserNotFoundByEmailException(String email) {
		super("user does not found! email: " + email);
	}

}
