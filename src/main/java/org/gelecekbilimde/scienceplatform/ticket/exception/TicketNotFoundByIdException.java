package org.gelecekbilimde.scienceplatform.ticket.exception;

import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;

import java.io.Serial;

public final class TicketNotFoundByIdException extends AbstractNotFoundException {

	@Serial
	private static final long serialVersionUID = 2578307933658765882L;

	public TicketNotFoundByIdException(Long id) {
		super("ticket not found by id: " + id);
	}

}
