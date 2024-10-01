package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class RoleChangeInAssesmentException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = -5805556733537568514L;

	public RoleChangeInAssesmentException() {
		super("User has role change request!");
	}

}
