package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public final class RoleNotFoundByNameException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -4700198439829934412L;

	public RoleNotFoundByNameException(String name) {
		super("role not found with name: " + name);
	}

}
