package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;

import java.io.Serial;

public class RoleApplicationAlreadyConcludedException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -4854149711093320907L;

	public RoleApplicationAlreadyConcludedException(Long id) {
		super("role application already concluded id: " + id);
	}

}
