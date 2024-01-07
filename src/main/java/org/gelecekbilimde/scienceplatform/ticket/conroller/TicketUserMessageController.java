package org.gelecekbilimde.scienceplatform.ticket.conroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.Response;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.MessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/ticket_message")
class TicketUserMessageController {

	private final TicketMessageService ticketService;

	@GetMapping
	@PreAuthorize("hasAuthority('self:ticket:read')")
	public Response<PagingResponse<MessageResponse>> ticketMessageRead(@Valid TicketMessageRequest request) {
		final PagingResponse<MessageResponse> ticketResponses = ticketService.ticketMessageReadSelf(request);
		return Response.ok(ticketResponses);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('self:ticket:create')")
	public Response<MessageResponse> ticketCreateSelf(@RequestBody @Valid TicketMessageCreateRequest request) {
		MessageResponse ticketResponse = ticketService.ticketMessageCreate(request);
		return Response.create(ticketResponse);
	}


}
