package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketMessageResponse;

public interface TicketMessageService {

	PagingResponse<TicketMessageResponse> ticketMessageRead(TicketMessageRequest request);

	PagingResponse<TicketMessageResponse> ticketMessageReadSelf(TicketMessageRequest request);

	TicketMessageResponse ticketMessageCreate(TicketMessageCreateRequest request);

}
