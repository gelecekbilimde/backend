package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public class ServerException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -4100558223254968385L;

	public ServerException(String message) {
		super(message);
	}

}
