package org.gelecekbilimde.scienceplatform.exception;

public class UserVerifyException extends RuntimeException {
	public UserVerifyException(String message) {
		super(message);
	}

	public UserVerifyException(String message, Throwable cause) {
		super(message, cause);
	}
}
