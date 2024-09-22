package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractAuthException;

import java.io.Serial;

public final class TokenNotValidException extends AbstractAuthException {

	@Serial
	private static final long serialVersionUID = 6107727975637275804L;

	public TokenNotValidException() {
		super("token does not valid");
	}

}
