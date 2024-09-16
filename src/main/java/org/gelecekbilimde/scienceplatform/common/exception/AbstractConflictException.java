package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public abstract class AbstractConflictException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -8368661707301414245L;

	protected AbstractConflictException(String message) {
		super(message);
	}

}
