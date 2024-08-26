package org.gelecekbilimde.scienceplatform.auth.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serial;

public final class UserNotFoundException extends UsernameNotFoundException {

	@Serial
	private static final long serialVersionUID = -6354173425539337989L;

	public UserNotFoundException() {
		super("User not found!");
	}
	public UserNotFoundException(String message) {
		super(message);
	}

}
