package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public class ClientException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -7931419428203879453L;

	public ClientException(String message) {
		super(message);
	}

}
