package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public class NotAllowedException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 7937842478423838127L;

	public NotAllowedException(String message) {
		super(message);
	}

}
