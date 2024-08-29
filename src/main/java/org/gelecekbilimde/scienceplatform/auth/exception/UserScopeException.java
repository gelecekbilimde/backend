package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class UserScopeException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 2431091153155759164L;

	public UserScopeException() {
		super("User Scope has a problem");
	}
}
