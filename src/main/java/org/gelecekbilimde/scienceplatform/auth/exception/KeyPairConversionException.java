package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractServerException;

import java.io.Serial;

public final class KeyPairConversionException extends AbstractServerException {

	@Serial
	private static final long serialVersionUID = 304959574320527271L;

	public KeyPairConversionException(Throwable cause) {
		super("error occurred while converting key pair", cause);
	}

}
