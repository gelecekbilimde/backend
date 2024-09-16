package org.gelecekbilimde.scienceplatform.auth.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serial;

public final class UserNotFoundByEmailException extends UsernameNotFoundException {

	@Serial
	private static final long serialVersionUID = -6354173425539337989L;

	public UserNotFoundByEmailException() {
		super("User not found!");
	}

	public UserNotFoundByEmailException(String email) {
		super("User not found! " + email);
	}

}
