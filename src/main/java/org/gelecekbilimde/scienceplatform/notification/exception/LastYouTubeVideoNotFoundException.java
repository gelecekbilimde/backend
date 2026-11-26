package org.gelecekbilimde.scienceplatform.notification.exception;

import java.io.Serial;

public final class LastYouTubeVideoNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 3026069869731133992L;

	public LastYouTubeVideoNotFoundException(Throwable cause) {
		super("last YouTube video not found!", cause);
	}

}
