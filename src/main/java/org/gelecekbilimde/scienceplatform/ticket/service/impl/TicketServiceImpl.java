package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.ticket.exception.TicketAlreadyHasStatusException;
import org.gelecekbilimde.scienceplatform.ticket.exception.TicketNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
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
	private final Identity identity;


	@Override
	public BasePage<Ticket> findAll(final TicketListRequest listRequest) {
		return ticketReadPort
			.findAll(listRequest.getPageable(), listRequest.getFilter());
	}


	@Override
	public Ticket findById(final Long id) {

		final Ticket ticket = ticketReadPort.findById(id)
			.orElseThrow(() -> new TicketNotFoundByIdException(id));

		boolean isNotAdmin = !identity.isAdmin();
		boolean isNotOwner = !ticket.getUserId().equals(identity.getUserId());
		if (isNotAdmin && isNotOwner) {
			throw new TicketNotFoundByIdException(id);
		}

		return ticket;
	}


	@Override
	public void create(final TicketCreateRequest createRequest) {

		final Ticket ticket = Ticket.builder()
			.userId(identity.getUserId())
			.title(createRequest.getTitle())
			.description(createRequest.getDescription())
			.build();

		ticketSavePort.save(ticket);
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
