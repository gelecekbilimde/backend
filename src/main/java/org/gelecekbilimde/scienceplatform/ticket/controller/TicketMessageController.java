package org.gelecekbilimde.scienceplatform.ticket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketMessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket-message")
class TicketMessageController {

	private final TicketMessageService ticketService;

	@GetMapping
	@PreAuthorize("hasAuthority('ticket:read')")
	public Response<PagingResponse<TicketMessageResponse>> ticketMessageRead(@Valid TicketMessageRequest request) {
		final PagingResponse<TicketMessageResponse> ticketResponses = ticketService.ticketMessageRead(request);
		return Response.ok(ticketResponses);
	}

}
