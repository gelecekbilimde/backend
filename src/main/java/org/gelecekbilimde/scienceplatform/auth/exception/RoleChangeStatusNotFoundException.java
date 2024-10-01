package org.gelecekbilimde.scienceplatform.auth.exception;

import java.io.Serial;

public class RoleChangeStatusNotFoundException extends IllegalArgumentException{

	@Serial
	private static final long serialVersionUID = -424280976813361741L;

	public RoleChangeStatusNotFoundException(String status){
		super("Invalid role change status: " + status);
	}

}
