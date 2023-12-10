package org.gelecekbilimde.scienceplatform.ticket.conroller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket_message")
class TicketMessageController {

	private final TicketMessageService ticketService;

	@GetMapping
	@PreAuthorize("hasAuthority('ticket:read')")
	public Response<PagingResponse<TicketResponse>> ticketMessageRead(@Valid TicketRequest request) {
		final PagingResponse<TicketResponse> ticketResponses = ticketService.ticketMessageRead(request);
		return Response.ok(ticketResponses);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('ticket:update')")
	public Response<TicketResponse> ticketMessageUpdate(@RequestBody @Valid TicketUpdateRequest request) {

		TicketResponse ticketResponse = ticketService.ticketMessageCreate(request);
		return Response.create(ticketResponse);
	}

}
