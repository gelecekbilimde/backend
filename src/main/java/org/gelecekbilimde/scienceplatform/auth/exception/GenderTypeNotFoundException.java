package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class GenderTypeNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -6123295058057764828L;

	public GenderTypeNotFoundException() {
		super("Gender type not found");
	}
}
