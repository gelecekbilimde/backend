package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;

public interface TicketSelfService {

	BasePage<Ticket> findAll(final TicketListRequest listRequest);

}
