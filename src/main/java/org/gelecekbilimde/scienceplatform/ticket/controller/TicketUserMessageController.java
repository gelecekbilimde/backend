package org.gelecekbilimde.scienceplatform.ticket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.Response;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketMessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/ticket-message")
class TicketUserMessageController {

	private final TicketMessageService ticketService;

	@GetMapping
	@PreAuthorize("hasAuthority('self:ticket:read')")
	public Response<PagingResponse<TicketMessageResponse>> ticketMessageRead(@Valid TicketMessageRequest request) {
		final PagingResponse<TicketMessageResponse> ticketResponses = ticketService.ticketMessageReadSelf(request);
		return Response.ok(ticketResponses);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('self:ticket:create')")
	public Response<TicketMessageResponse> ticketCreateSelf(@RequestBody @Valid TicketMessageCreateRequest request) {
		TicketMessageResponse ticketResponse = ticketService.ticketMessageCreate(request);
		return Response.create(ticketResponse);
	}


}
