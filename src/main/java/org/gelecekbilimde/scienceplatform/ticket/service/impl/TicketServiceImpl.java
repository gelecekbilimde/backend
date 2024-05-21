package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.model.Paging;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketEntityToResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketEntityToTicketMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketUpdateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketService;
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

	private final TicketEntityToResponseMapper ticketEntityToResponseMapper = TicketEntityToResponseMapper.initialize();
	private final TicketEntityToTicketMapper ticketEntityToTicketMapper = TicketEntityToTicketMapper.initialize();

	@Override
	public PagingResponse<TicketResponse> ticketRead(PagingRequest request) {
		Pageable pageable = request.toPageable();
		Page<TicketEntity> ticketPage = ticketRepository.findAll(pageable);
		List<TicketResponse> ticketResponses = ticketEntityToResponseMapper.map(ticketPage.getContent());
		final Paging<TicketResponse> posts = Paging.of(ticketPage, ticketResponses);
		return PagingResponse.<TicketResponse>builder().of(posts).content(ticketEntityToResponseMapper.map(ticketPage.getContent())).build();
	}

	@Override
	public Ticket updateTicket(TicketUpdateRequest request) {
		TicketEntity ticketEntity = ticketRepository.getReferenceById(request.getId()); // TODO : getReferenceById yerine findById kullanılmalı
		ticketEntity.setStatus(request.getStatus());
		ticketRepository.save(ticketEntity);
		return ticketEntityToTicketMapper.map(ticketEntity);
	}

	@Override
	public Ticket ticketCreateSelf(TicketCreateRequest request) {
		TicketEntity ticketEntity = TicketEntity.builder().userId(identity.getUserId()).message(request.getMessage()).build();
		TicketEntity saveTicketEntity = this.ticketRepository.save(ticketEntity);
		return ticketEntityToTicketMapper.map(saveTicketEntity);
	}

	@Override
	public PagingResponse<TicketResponse> ticketReadSelf(PagingRequest request) {
		Specification<TicketEntity> spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), identity.getUserId());
		Page<TicketEntity> ticketPage = ticketRepository.findAll(spec, request.toPageable());
		List<TicketResponse> ticketResponses = ticketEntityToResponseMapper.map(ticketPage.getContent());
		final Paging<TicketResponse> posts = Paging.of(ticketPage, ticketResponses);
		return PagingResponse.<TicketResponse>builder().of(posts).content(ticketEntityToResponseMapper.map(ticketPage.getContent())).build();
	}
}
