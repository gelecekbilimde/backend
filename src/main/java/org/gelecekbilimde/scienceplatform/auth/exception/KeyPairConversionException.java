package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class KeyPairConversionException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -714864829639781798L;

	public KeyPairConversionException(Throwable cause) {
		super("error occurred while converting key pair", cause);
	}

}
