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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
class TicketController {

	private final TicketService ticketService;

	@PostMapping("/")
	// @PreAuthorize("hasAuthority('ticket:read')")
	public Response<PagingResponse<TicketResponse>> readTicket(@ModelAttribute @Valid TicketUserListRequest request) {
		final PagingResponse<TicketResponse> ticketResponseList = ticketService.getTicketUserResponseList(request);
		return Response.ok(ticketResponseList);
	}

	@RequestMapping("/admin/ticket")
	// @PreAuthorize("hasAuthority('admin:ticket:read')")
	public Response<PagingResponse<TicketResponse>> getTicket(@ModelAttribute @Valid TicketListRequest request) {
		final PagingResponse<TicketResponse> ticketResponseList = ticketService.getTicketResponseList(request);
		return Response.ok(ticketResponseList);
	}

	@PostMapping("/create")
	// @PreAuthorize("hasAuthority('ticket:create')")
	public Response<TicketResponse> saveTicket(@RequestBody @Valid TicketCreateRequest request) {
		TicketResponse ticketResponse = ticketService.saveTicket(request);

		return Response.create(ticketResponse);
	}

	@PostMapping("/update")
	// @PreAuthorize("hasAuthority('ticket:update')")
	public Response<TicketResponse> saveTicket(@RequestBody @Valid TicketUpdateRequest request) {
		TicketResponse ticketResponse = ticketService.updateTicketStatus(request);
		return Response.create(ticketResponse);
	}

}
