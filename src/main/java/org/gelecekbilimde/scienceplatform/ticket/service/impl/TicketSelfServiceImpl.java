package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketReadPort;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketSelfService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TicketSelfServiceImpl implements TicketSelfService {

	private final TicketReadPort ticketReadPort;


	@Override
	public BasePage<Ticket> findAll(final TicketListRequest listRequest) {
		return ticketReadPort
			.findAll(listRequest.getPageable(), listRequest.getFilter());
	}

}
