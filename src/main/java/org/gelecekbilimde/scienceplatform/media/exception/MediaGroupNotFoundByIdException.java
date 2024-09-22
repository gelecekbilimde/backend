package org.gelecekbilimde.scienceplatform.media.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class MediaGroupNotFoundByIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = -8189248476870263706L;

	public MediaGroupNotFoundByIdException(Long id) {
		super("media group not found by id: " + id);
	}

}
