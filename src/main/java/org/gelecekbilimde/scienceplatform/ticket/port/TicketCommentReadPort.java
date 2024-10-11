package org.gelecekbilimde.scienceplatform.ticket.port;

import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;

import java.util.List;

public interface TicketCommentReadPort {

	List<TicketComment> findAllByTicketId(Long ticketId);

}
