package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class RoleNotFoundByNameException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -927147689852209182L;

	public RoleNotFoundByNameException(String name) {
		super("role does not found! name: " + name);
	}

}
