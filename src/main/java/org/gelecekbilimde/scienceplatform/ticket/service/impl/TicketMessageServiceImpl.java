package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.MessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.mapper.MessageToMessageResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketMessage;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketMessageRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
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
	private static final MessageToMessageResponseMapper messageToMessageResponseMapper = MessageToMessageResponseMapper.initialize();
	@Override
	public PagingResponse<MessageResponse> ticketMessageRead(TicketMessageRequest request) {
		Pageable pageable = request.toPageable();
		Page<TicketMessage> messagePage = ticketMessageRepository.findAll(pageable);
		List<MessageResponse> ticketResponses = messageToMessageResponseMapper.map(messagePage.getContent());
		final Paging<MessageResponse> posts = Paging.of(messagePage, ticketResponses);
		return PagingResponse.<MessageResponse>builder()
			.of(posts)
			.content(messageToMessageResponseMapper.map(messagePage.getContent()))
			.build();
	}

	@Override
	public PagingResponse<MessageResponse> ticketMessageReadSelf(TicketMessageRequest request) {
		Specification<TicketMessage> spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), identity.getUserId());
		Page<TicketMessage> messagePage = ticketMessageRepository.findAll(spec, request.toPageable());
		List<MessageResponse> messageResponses = messageToMessageResponseMapper.map(messagePage.getContent());
		final Paging<MessageResponse> messageResponsesPage = Paging.of(messagePage, messageResponses);
		return PagingResponse.<MessageResponse>builder()
			.of(messageResponsesPage)
			.content(messageToMessageResponseMapper.map(messagePage.getContent()))
			.build();
	}

	@Override
	public MessageResponse ticketMessageCreate(TicketMessageCreateRequest request) {
		TicketMessage message = TicketMessage.builder().userId(identity.getUserId()).ticketId(request.getId()).message(request.getMessage()).build();
		TicketMessage saveTicket = this.ticketMessageRepository.save(message);
		return messageToMessageResponseMapper.map(saveTicket);
	}
}
