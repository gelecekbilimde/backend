package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketListRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUserListRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.mapper.TicketToTicketResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

	private final TicketRepository ticketRepository;

	private static final TicketToTicketResponseMapper ticketModelToTicketResponseMapper = TicketToTicketResponseMapper.initialize();

	@Override
	public TicketResponse saveTicket(TicketCreateRequest ticketCreateRequest) {
		Ticket ticket = Ticket.builder().userId(ticketCreateRequest.getUserId()).message(ticketCreateRequest.getMessage()).build();
		Ticket saveTicket = this.ticketRepository.save(ticket);
		return ticketModelToTicketResponseMapper.map(saveTicket);
	}

	@Override
	public TicketResponse updateTicketStatus(TicketUpdateRequest ticketCreateRequest) {
		this.ticketRepository.updateStatusById(ticketCreateRequest.getTicketId(), ticketCreateRequest.getStatus());
		Ticket saveTicket = ticketRepository.getReferenceById(ticketCreateRequest.getTicketId());
		return ticketModelToTicketResponseMapper.map(saveTicket);
	}

	@Override
	public PagingResponse<TicketResponse> getTicketResponseList(TicketListRequest listRequest) {
		Page<Ticket> ticketPage = ticketRepository.findAll(listRequest.toPageable());
		List<TicketResponse> ticketResponseList = ticketModelToTicketResponseMapper.map(ticketPage.getContent());
		final Paging<TicketResponse> postList = Paging.of(ticketPage, ticketResponseList);
		return PagingResponse.<TicketResponse>builder().of(postList).content(ticketModelToTicketResponseMapper.map(ticketPage.getContent())).build();
	}

	@Override
	public PagingResponse<TicketResponse> getTicketUserResponseList(TicketUserListRequest listRequest) {
		Page<Ticket> ticketPage = ticketRepository.getByUserId(listRequest.getUserId(), listRequest.toPageable());
		List<TicketResponse> ticketResponseList = ticketModelToTicketResponseMapper.map(ticketPage.getContent());
		final Paging<TicketResponse> postList = Paging.of(ticketPage, ticketResponseList);
		return PagingResponse.<TicketResponse>builder().of(postList).content(ticketModelToTicketResponseMapper.map(ticketPage.getContent())).build();
	}

}
