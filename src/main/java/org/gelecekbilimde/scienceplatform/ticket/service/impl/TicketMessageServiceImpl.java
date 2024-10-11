package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.response.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketMessageEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketMessageEntityToMessageResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketMessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketMessageRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TicketMessageServiceImpl implements TicketMessageService {

	private final Identity identity;

	private final TicketMessageRepository ticketMessageRepository;

	private final TicketMessageEntityToMessageResponseMapper ticketMessageEntityToMessageResponseMapper = TicketMessageEntityToMessageResponseMapper.initialize();


	@Override
	public PagingResponse<TicketMessageResponse> ticketMessageRead(TicketMessageRequest request) {
		Pageable pageable = request.getPageable().toPageable();
		Page<TicketMessageEntity> messagePage = ticketMessageRepository.findAll(pageable);
		List<TicketMessageResponse> ticketResponses = ticketMessageEntityToMessageResponseMapper.map(messagePage.getContent());
		final BasePage<TicketMessageResponse> posts = BasePage.of(messagePage, ticketResponses);
		return PagingResponse.<TicketMessageResponse>builder()
			.of(posts)
			.content(ticketMessageEntityToMessageResponseMapper.map(messagePage.getContent()))
			.build();
	}

	@Override
	public PagingResponse<TicketMessageResponse> ticketMessageReadSelf(TicketMessageRequest request) {
		Specification<TicketMessageEntity> spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), identity.getUserId());
		Page<TicketMessageEntity> messagePage = ticketMessageRepository.findAll(spec, request.getPageable().toPageable());
		List<TicketMessageResponse> ticketMessageRespons = ticketMessageEntityToMessageResponseMapper.map(messagePage.getContent());
		final BasePage<TicketMessageResponse> messageResponsesPage = BasePage.of(messagePage, ticketMessageRespons);
		return PagingResponse.<TicketMessageResponse>builder()
			.of(messageResponsesPage)
			.content(ticketMessageEntityToMessageResponseMapper.map(messagePage.getContent()))
			.build();
	}

	@Override
	public TicketMessageResponse ticketMessageCreate(TicketMessageCreateRequest request) {
		TicketMessageEntity message = TicketMessageEntity.builder().userId(identity.getUserId()).ticketId(request.getId()).content(request.getMessage()).build();
		TicketMessageEntity saveTicket = this.ticketMessageRepository.save(message);
		return ticketMessageEntityToMessageResponseMapper.map(saveTicket);
	}
}
