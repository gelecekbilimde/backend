package org.gelecekbilimde.scienceplatform.ticket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
class TicketController {

	private final TicketService ticketService;

	@GetMapping
	@PreAuthorize("hasAuthority('ticket:read')")
	SuccessResponse<PagingResponse<TicketResponse>> ticketRead(@Valid PagingRequest request) {
		final PagingResponse<TicketResponse> ticketResponses = ticketService.ticketRead(request);
		return SuccessResponse.success(ticketResponses);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('ticket:update')")
	SuccessResponse<Ticket> ticketUpdate(@RequestBody @Valid TicketUpdateRequest request) {
		Ticket ticketResponse = ticketService.updateTicket(request);
		return SuccessResponse.success(ticketResponse);
	}

}
