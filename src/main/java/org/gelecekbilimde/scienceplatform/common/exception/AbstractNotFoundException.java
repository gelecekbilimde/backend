package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public abstract class AbstractNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -5515927790433599004L;

	protected AbstractNotFoundException(String message) {
		super(message);
	}

}
