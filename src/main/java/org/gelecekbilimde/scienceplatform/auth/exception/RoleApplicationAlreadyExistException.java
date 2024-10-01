package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public class RoleApplicationAlreadyExistException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = 3834570698226426742L;

	public RoleApplicationAlreadyExistException() {
		super("user already has a role application in review");
	}

}
