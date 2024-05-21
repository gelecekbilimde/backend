package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -6064752788138475944L;

	public NotFoundException(String message) {
		super(message);
	}

}
