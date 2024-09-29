package org.gelecekbilimde.scienceplatform.auth.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;
import java.io.Serial;

public class RoleChangeNotFoundByIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -5183101384185275492L;

	public RoleChangeNotFoundByIdException(Long id) {
		super("RoleName change does not found! id: " + id);
	}

}
