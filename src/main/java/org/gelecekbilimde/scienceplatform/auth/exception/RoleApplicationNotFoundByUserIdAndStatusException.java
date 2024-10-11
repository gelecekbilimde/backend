package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class RoleApplicationNotFoundByUserIdAndStatusException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -8360751775119161646L;

	public RoleApplicationNotFoundByUserIdAndStatusException(String userId, RoleApplicationStatus status) {
		super("role application not found! userId: " + userId + ", status: " + status);
	}

}
