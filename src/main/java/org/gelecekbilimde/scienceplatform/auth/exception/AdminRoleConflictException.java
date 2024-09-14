package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class AdminRoleConflictException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1946867346643338386L;

	public AdminRoleConflictException() {
		super("User's role is already admin");
	}
}
