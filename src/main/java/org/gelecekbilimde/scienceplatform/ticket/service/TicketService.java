package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketResponse;


public interface TicketService {

	PagingResponse<TicketResponse> ticketRead(PagingRequest request);

	PagingResponse<TicketResponse> ticketReadSelf(PagingRequest request);

	Ticket updateTicket(TicketUpdateRequest request);

	Ticket ticketCreateSelf(TicketCreateRequest request);

}
