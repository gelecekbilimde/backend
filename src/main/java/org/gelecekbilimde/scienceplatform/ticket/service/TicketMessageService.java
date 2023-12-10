package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;


public interface TicketMessageService {

	PagingResponse<TicketResponse> ticketMessageRead(TicketRequest request);

	PagingResponse<TicketResponse> ticketMessageReadSelf(TicketRequest request);

	TicketResponse ticketMessageCreate(TicketUpdateRequest request);

	TicketResponse ticketMessageCreateSelf(TicketCreateRequest request);

}
