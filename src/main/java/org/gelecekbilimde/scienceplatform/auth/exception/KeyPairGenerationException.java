package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractServerException;

import java.io.Serial;

public class KeyPairGenerationException extends AbstractServerException {

	@Serial
	private static final long serialVersionUID = 7666167175325870801L;

	public KeyPairGenerationException(Throwable cause) {
		super("error occurred while generating key pair", cause);
	}

}
