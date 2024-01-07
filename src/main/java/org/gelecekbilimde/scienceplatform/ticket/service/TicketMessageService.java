package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.MessageResponse;

public interface TicketMessageService {

	PagingResponse<MessageResponse> ticketMessageRead(TicketMessageRequest request);

	PagingResponse<MessageResponse> ticketMessageReadSelf(TicketMessageRequest request);

	MessageResponse ticketMessageCreate(TicketMessageCreateRequest request);

}
