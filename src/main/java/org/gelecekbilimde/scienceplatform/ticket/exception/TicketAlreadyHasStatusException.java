package org.gelecekbilimde.scienceplatform.ticket.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractConflictException;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

import java.io.Serial;

public final class TicketAlreadyHasStatusException extends AbstractConflictException {

	@Serial
	private static final long serialVersionUID = -1475053585476383993L;

	public TicketAlreadyHasStatusException(TicketStatus status) {
		super("ticket already has " + status + " status");
	}

}
