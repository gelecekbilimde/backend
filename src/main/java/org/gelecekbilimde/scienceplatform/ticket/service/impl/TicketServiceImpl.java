package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingRequest;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.domain.TicketDomain;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.mapper.TicketModelToTicketDomainMapper;
import org.gelecekbilimde.scienceplatform.ticket.mapper.TicketToTicketResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
class TicketServiceImpl implements TicketService {

	private final Identity identity;

	private final TicketRepository ticketRepository;

	private static final TicketToTicketResponseMapper ticketModelToTicketResponseMapper = TicketToTicketResponseMapper.initialize();

	private static final TicketModelToTicketDomainMapper ticketModelToTicketDomainMapper = TicketModelToTicketDomainMapper.initialize();

	@Override
	public PagingResponse<TicketResponse> ticketRead(PagingRequest request) {
		Pageable pageable = request.toPageable();
		Page<Ticket> ticketPage = ticketRepository.findAll(pageable);
		List<TicketResponse> ticketResponses = ticketModelToTicketResponseMapper.map(ticketPage.getContent());
		final Paging<TicketResponse> posts = Paging.of(ticketPage, ticketResponses);
		return PagingResponse.<TicketResponse>builder().of(posts).content(ticketModelToTicketResponseMapper.map(ticketPage.getContent())).build();
	}

	@Override
	public TicketDomain updateTicket(TicketUpdateRequest request) {
		Ticket ticket = ticketRepository.getReferenceById(request.getId());
		ticket.setStatus(request.getStatus());
		ticketRepository.save(ticket);
		return ticketModelToTicketDomainMapper.map(ticket);
	}

	@Override
	public TicketDomain ticketCreateSelf(TicketCreateRequest request) {
		Ticket ticket = Ticket.builder().userId(identity.getUserId()).message(request.getMessage()).build();
		Ticket saveTicket = this.ticketRepository.save(ticket);
		return ticketModelToTicketDomainMapper.map(saveTicket);
	}

	@Override
	public PagingResponse<TicketResponse> ticketReadSelf(PagingRequest request) {
		Specification<Ticket> spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), identity.getUserId());
		Page<Ticket> ticketPage = ticketRepository.findAll(spec, request.toPageable());
		List<TicketResponse> ticketResponses = ticketModelToTicketResponseMapper.map(ticketPage.getContent());
		final Paging<TicketResponse> posts = Paging.of(ticketPage, ticketResponses);
		return PagingResponse.<TicketResponse>builder().of(posts).content(ticketModelToTicketResponseMapper.map(ticketPage.getContent())).build();
	}
}
