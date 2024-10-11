package org.gelecekbilimde.scienceplatform.ticket.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketToResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketToTicketsResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketsResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class TicketController {

	private final TicketService ticketService;

	private final TicketToTicketsResponseMapper ticketToTicketsResponseMapper = TicketToTicketsResponseMapper.initialize();
	private final TicketToResponseMapper ticketToResponseMapper = TicketToResponseMapper.initialize();


	@PostMapping("/tickets")
	@PreAuthorize("hasAuthority('ticket:list')")
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


	@PostMapping("/ticket/{id}")
	@PreAuthorize("hasAuthority('ticket:detail')")
	SuccessResponse<TicketResponse> findById(@PathVariable @Positive Long id) {
		Ticket ticket = ticketService.findById(id);
		TicketResponse ticketResponse = ticketToResponseMapper.map(ticket);
		return SuccessResponse.success(ticketResponse);
	}


	@PostMapping("/ticket")
	@PreAuthorize("hasAuthority('ticket:create')")
	SuccessResponse<Void> create(@RequestBody @Valid TicketCreateRequest createRequest) {
		ticketService.create(createRequest);
		return SuccessResponse.success();
	}


	@PutMapping("/ticket/{id}")
	@PreAuthorize("hasAuthority('ticket:update')")
	SuccessResponse<Void> update(@PathVariable @Positive Long id,
								 @RequestBody @Valid TicketUpdateRequest request) {

		ticketService.update(id, request);
		return SuccessResponse.success();
	}

}
