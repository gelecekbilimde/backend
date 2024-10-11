package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketUpdateRequest;


public interface TicketService {

	BasePage<Ticket> findAll(final TicketListRequest listRequest);

	void update(Long id, TicketUpdateRequest updateRequest);

	BasePage<Ticket> findAllSelf(final TicketListRequest listRequest);

	void create(TicketCreateRequest request);

}
