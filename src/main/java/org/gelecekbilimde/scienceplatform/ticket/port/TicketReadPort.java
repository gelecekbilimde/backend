package org.gelecekbilimde.scienceplatform.ticket.port;

import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketFilter;

import java.util.Optional;

public interface TicketReadPort {

	BasePage<Ticket> findAll(BasePageable basePageable, TicketFilter filter);

	Optional<Ticket> findById(Long id);

}
