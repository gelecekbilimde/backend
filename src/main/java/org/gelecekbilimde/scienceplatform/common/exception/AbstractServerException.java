package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public abstract class AbstractServerException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3135425827857563572L;

	protected AbstractServerException(String message) {
		super(message);
	}

	protected AbstractServerException(String message, Throwable cause) {
		super(message, cause);
	}

}
