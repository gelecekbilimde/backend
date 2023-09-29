package org.gelecekbilimde.scienceplatform.ticket.service;

import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUserListRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;


public interface TicketService {


	TicketResponse saveTicket(TicketCreateRequest ticketCreateRequest);

	TicketResponse updateTicketStatus(TicketUpdateRequest ticketCreateRequest);

	PagingResponse<TicketResponse> getTicketResponseList(TicketListRequest listRequest);

	PagingResponse<TicketResponse> getTicketUserResponseList(TicketUserListRequest listRequest);

}
