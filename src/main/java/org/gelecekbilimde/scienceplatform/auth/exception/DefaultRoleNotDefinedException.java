package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class DefaultRoleNotDefinedException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -7607911331936323738L;

	public DefaultRoleNotDefinedException() {
		super("Default Role is not defined.");
	}
}
