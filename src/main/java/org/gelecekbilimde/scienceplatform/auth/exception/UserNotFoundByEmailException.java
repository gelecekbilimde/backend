package org.gelecekbilimde.scienceplatform.auth.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serial;

public final class UserNotFoundByEmailException extends UsernameNotFoundException {

	@Serial
	private static final long serialVersionUID = 3275707458511858867L;

	public UserNotFoundByEmailException() {
		super("user does not found!");
	}

	public UserNotFoundByEmailException(String email) {
		super("user does not found! email: " + email);
	}

}
