package org.gelecekbilimde.scienceplatform.ticket.conroller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUserListRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
class TicketController {

	private final TicketService ticketService;

	@GetMapping("/")
	@PreAuthorize("hasAuthority('ticket:all:read')")
	public Response<PagingResponse<TicketResponse>> getTicket(@ModelAttribute @Valid TicketListRequest request) {
		final PagingResponse<TicketResponse> ticketResponses = ticketService.getTicketResponseList(request);
		return Response.ok(ticketResponses);
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('ticket:read')")
	public Response<PagingResponse<TicketResponse>> readTicket(@ModelAttribute @Valid TicketUserListRequest request) {
		final PagingResponse<TicketResponse> ticketResponses = ticketService.getTicketUserResponseList(request);
		return Response.ok(ticketResponses);
	}

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ticket:create')")
	public Response<TicketResponse> saveTicket(@RequestBody @Valid TicketCreateRequest request) {
		TicketResponse ticketResponse = ticketService.saveTicket(request);
		return Response.create(ticketResponse);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAuthority('ticket:all:update')")
	public Response<TicketResponse> saveTicket(@RequestBody @Valid TicketUpdateRequest request) {
		TicketResponse ticketResponse = ticketService.updateTicketStatus(request);
		return Response.create(ticketResponse);
	}

}
