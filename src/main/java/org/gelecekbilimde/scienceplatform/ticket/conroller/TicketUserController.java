package org.gelecekbilimde.scienceplatform.ticket.conroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/ticket")
class TicketUserController {

	private final TicketService ticketService;

	@GetMapping
	@PreAuthorize("hasAuthority('self:ticket:read')")
	public Response<PagingResponse<TicketResponse>> ticketReadSelf(TicketRequest request) {
		final PagingResponse<TicketResponse> ticketResponses = ticketService.ticketReadSelf(request);
		return Response.ok(ticketResponses);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('self:ticket:create')")
	public Response<TicketResponse> ticketCreateSelf(@RequestBody @Valid TicketCreateRequest request) {
		TicketResponse ticketResponse = ticketService.ticketCreateSelf(request);
		return Response.create(ticketResponse);
	}

}
