package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketReadPort;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketSavePort;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketSelfService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TicketSelfServiceImpl implements TicketSelfService {

	private final TicketReadPort ticketReadPort;
	private final TicketSavePort ticketSavePort;
	private final Identity identity;


	@Override
	public BasePage<Ticket> findAll(final TicketListRequest listRequest) {
		return ticketReadPort
			.findAll(listRequest.getPageable(), listRequest.getFilter());
	}


	@Override
	public void create(final TicketCreateRequest createRequest) {

		Ticket ticket = Ticket.builder()
			.userId(identity.getUserId())
			.title(createRequest.getTitle())
			.description(createRequest.getDescription())
			.build();

		ticketSavePort.save(ticket);
	}

}
