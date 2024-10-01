package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class RoleNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -6838918501298801111L;

	public RoleNotFoundException() {
		super("RoleName does not found!");
	}

}
