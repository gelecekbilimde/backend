package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;


public interface TicketService {

	PagingResponse<TicketResponse> ticketRead(TicketRequest request);

	PagingResponse<TicketResponse> ticketReadSelf(TicketRequest request);

	TicketResponse updateTicket(TicketUpdateRequest request);

	TicketResponse ticketCreateSelf(TicketCreateRequest request);

}
