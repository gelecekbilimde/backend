package org.gelecekbilimde.scienceplatform.ticket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketToTicketsResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketsResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class TicketUserController {

	private final TicketService ticketService;


	private final TicketToTicketsResponseMapper ticketToTicketsResponseMapper = TicketToTicketsResponseMapper.initialize();


	@PostMapping("/tickets/self")
	@PreAuthorize("hasAuthority('ticket:self:list')")
	SuccessResponse<PagingResponse<TicketsResponse>> findAll(@RequestBody @Valid TicketListRequest listRequest) {

		final BasePage<Ticket> pageOfTickets = ticketService.findAll(listRequest);

		final PagingResponse<TicketsResponse> pageResponseOfTicket = PagingResponse
			.<TicketsResponse>builder()
			.of(pageOfTickets)
			.content(
				ticketToTicketsResponseMapper.map(pageOfTickets.getContent())
			)
			.filteredBy(listRequest.getFilter())
			.build();

		return SuccessResponse.success(pageResponseOfTicket);
	}


	@PostMapping
	@PreAuthorize("hasAuthority('ticket:self:create')")
	SuccessResponse<Void> create(@RequestBody @Valid TicketCreateRequest createRequest) {
		ticketService.create(createRequest);
		return SuccessResponse.success();
	}

}
