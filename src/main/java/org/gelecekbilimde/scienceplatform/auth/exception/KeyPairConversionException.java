package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractServerException;

import java.io.Serial;

public class KeyPairConversionException extends AbstractServerException {

	@Serial
	private static final long serialVersionUID = -714864829639781798L;

	public KeyPairConversionException(Throwable cause) {
		super("error occurred while converting key pair", cause);
	}

}
