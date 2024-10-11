package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class RoleApplicationNotFoundByIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -1036422987236610575L;

	public RoleApplicationNotFoundByIdException(String id) {
		super("role application does not found! id: " + id);
	}

}
