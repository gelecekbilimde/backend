package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public class RoleApplicationNotFoundByIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -2344799760142647816L;

	public RoleApplicationNotFoundByIdException(Long id) {
		super("role application does not found! id: " + id);
	}

}
