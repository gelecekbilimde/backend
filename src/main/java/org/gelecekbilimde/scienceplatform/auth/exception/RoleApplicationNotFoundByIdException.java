package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public class RoleApplicationNotFoundByIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -7420720906649254998L;

	public RoleApplicationNotFoundByIdException(String id) {
		super("role application does not found! id: " + id);
	}

}
