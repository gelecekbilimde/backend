package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.mapper.TicketToTicketResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
class TicketMessageServiceImpl implements TicketMessageService {

	private final Identity identity;

	private final TicketRepository ticketRepository;

	private static final TicketToTicketResponseMapper ticketModelToTicketResponseMapper = TicketToTicketResponseMapper.initialize();


	@Override
	public PagingResponse<TicketResponse> ticketMessageRead(TicketRequest request) {
		return null;
	}

	@Override
	public PagingResponse<TicketResponse> ticketMessageReadSelf(TicketRequest request) {
		return null;
	}

	@Override
	public TicketResponse ticketMessageCreate(TicketUpdateRequest request) {
		return null;
	}

	@Override
	public TicketResponse ticketMessageCreateSelf(TicketCreateRequest request) {
		return null;
	}
}
