package org.gelecekbilimde.scienceplatform.ticket.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.response.SuccessResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketCommentToResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCommentAddRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketCommentResponse;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketCommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
class TicketCommentController {

	private final TicketCommentService ticketService;


	private final TicketCommentToResponseMapper ticketCommentToResponseMapper = TicketCommentToResponseMapper.initialize();


	@GetMapping("/ticket/{id}/comments")
	@PreAuthorize("hasAuthority('ticket:comment:list')")
	SuccessResponse<List<TicketCommentResponse>> findAllByTicketId(@PathVariable(name = "id") @Positive Long ticketId) {

		final List<TicketComment> ticketComments = ticketService.findAllByTicketId(ticketId);

		final List<TicketCommentResponse> ticketCommentResponses = ticketCommentToResponseMapper
			.map(ticketComments);
		return SuccessResponse.success(ticketCommentResponses);
	}


	@PostMapping("/ticket/{id}/comment")
	@PreAuthorize("hasAuthority('ticket:comment:add')")
	SuccessResponse<Void> add(@PathVariable(name = "id") @Positive Long ticketId,
							  @RequestBody @Valid TicketCommentAddRequest addRequest) {

		ticketService.add(ticketId, addRequest);
		return SuccessResponse.success();
	}

}
