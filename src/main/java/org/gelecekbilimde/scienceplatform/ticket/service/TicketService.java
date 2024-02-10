package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.PagingRequest;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.domain.TicketDomain;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;


public interface TicketService {

	PagingResponse<TicketResponse> ticketRead(PagingRequest request);

	PagingResponse<TicketResponse> ticketReadSelf(PagingRequest request);

	TicketDomain updateTicket(TicketUpdateRequest request);

	TicketDomain ticketCreateSelf(TicketCreateRequest request);

}
