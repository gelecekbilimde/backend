package org.gelecekbilimde.scienceplatform.common.exception;

import java.io.Serial;

public final class FileReadException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 7827987875237716345L;

	public FileReadException(Throwable cause) {
		super("Error occurred while reading file", cause);
	}

}
