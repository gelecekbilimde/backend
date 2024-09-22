package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractAuthException;

import java.io.Serial;

public class WrongEmailOrPasswordException extends AbstractAuthException {

	@Serial
	private static final long serialVersionUID = 5303255521009510109L;

	public WrongEmailOrPasswordException() {
		super("wrong email or password!");
	}

}
