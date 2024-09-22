package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public abstract class AbstractAuthException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 5834113326065055615L;

	protected AbstractAuthException(String message) {
		super(message);
	}

}
