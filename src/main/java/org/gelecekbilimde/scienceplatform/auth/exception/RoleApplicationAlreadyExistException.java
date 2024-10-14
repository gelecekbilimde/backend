package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class RoleApplicationAlreadyExistException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = 8186687324812319190L;

	public RoleApplicationAlreadyExistException() {
		super("user already has a role application in review");
	}

}
