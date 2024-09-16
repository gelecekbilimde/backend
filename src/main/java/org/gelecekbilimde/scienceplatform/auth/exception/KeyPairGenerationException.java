package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class KeyPairGenerationException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 7472270543754656424L;

	public KeyPairGenerationException(Throwable cause) {
		super("error occurred while generating key pair", cause);
	}

}
