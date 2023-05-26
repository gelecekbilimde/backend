package org.gelecekbilimde.scienceplatform.exception;

public class NotAllowedException extends RuntimeException {
	public NotAllowedException(String message) {
		super(message);
	}

	public NotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

}
