package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public final class RoleApplicationAlreadyConcludedException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = 6963403850981238210L;

	public RoleApplicationAlreadyConcludedException(String id) {
		super("role application already concluded id: " + id);
	}

}
