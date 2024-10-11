package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCommentAddRequest;

import java.util.List;

public interface TicketCommentService {

	List<TicketComment> findAllByTicketId(Long ticketId);

	void add(Long ticketId, TicketCommentAddRequest request);

}
