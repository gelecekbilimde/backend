package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class AlreadyRegisteredException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -8120776112921535480L;

	public AlreadyRegisteredException(String email) {
		super("This user is already registered" + email);
	}
}
