package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractAuthException;

import java.io.Serial;

public class UserPasswordNotValidException extends AbstractAuthException {

	@Serial
	private static final long serialVersionUID = 359664997679732461L;

	public UserPasswordNotValidException() {
		super("user password is not valid");
	}

}
