package org.gelecekbilimde.scienceplatform.user.exception;

import java.io.Serial;

public class AuthorRequestNotFound extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 4274114506886296673L;

	public AuthorRequestNotFound() {
		super("Author request not found");
	}
}
