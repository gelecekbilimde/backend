package org.gelecekbilimde.scienceplatform.user.exception;

import java.io.Serial;

public class UserRoleConflictException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 9055066854003445418L;

	public UserRoleConflictException() {
		super("User's role is not user");
	}
}
