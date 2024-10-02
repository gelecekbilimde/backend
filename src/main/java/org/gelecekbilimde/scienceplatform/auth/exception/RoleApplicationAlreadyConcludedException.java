package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public class RoleApplicationAlreadyConcludedException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -7807528937254572325L;

	public RoleApplicationAlreadyConcludedException(String id) {
		super("role application already concluded id: " + id);
	}

}
