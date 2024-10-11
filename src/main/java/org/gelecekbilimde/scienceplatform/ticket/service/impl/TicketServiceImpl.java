package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.ticket.exception.TicketAlreadyHasStatusException;
import org.gelecekbilimde.scienceplatform.ticket.exception.TicketNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketReadPort;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketSavePort;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TicketServiceImpl implements TicketService {

	private final TicketReadPort ticketReadPort;
	private final TicketSavePort ticketSavePort;

	@Override
	public BasePage<Ticket> findAll(final TicketListRequest listRequest) {
		return ticketReadPort
			.findAll(listRequest.getPageable(), listRequest.getFilter());
	}

	@Override
	public void update(final Long id,
					   final TicketUpdateRequest updateRequest) {

		final Ticket ticket = ticketReadPort.findById(id)
			.orElseThrow(() -> new TicketNotFoundByIdException(id));

		if (updateRequest.getStatus() == ticket.getStatus()) {
			throw new TicketAlreadyHasStatusException(ticket.getStatus());
		}

		ticket.setStatus(updateRequest.getStatus());
		ticketSavePort.save(ticket);
	}

}
