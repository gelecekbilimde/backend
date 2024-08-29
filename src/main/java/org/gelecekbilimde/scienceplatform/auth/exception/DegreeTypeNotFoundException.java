package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class DegreeTypeNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -430764556834245652L;

	public DegreeTypeNotFoundException() {
		super("Degree type not found");
	}
}
