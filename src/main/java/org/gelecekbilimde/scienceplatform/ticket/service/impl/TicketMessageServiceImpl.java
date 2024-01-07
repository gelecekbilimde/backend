package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.Paging;
import org.gelecekbilimde.scienceplatform.common.PagingResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageCreateRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.request.TicketMessageRequest;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.MessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.mapper.MessageToMessageResponseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Message;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketMessageRepository;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketMessageService;
import org.gelecekbilimde.scienceplatform.user.service.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		Page<Message> ticketPage = ticketMessageRepository.findAll(pageable);
		List<MessageResponse> ticketResponses = messageToMessageResponseMapper.map(ticketPage.getContent());
		final Paging<MessageResponse> posts = Paging.of(ticketPage, ticketResponses);
		return PagingResponse.<MessageResponse>builder().of(posts).content(messageToMessageResponseMapper.map(ticketPage.getContent())).build();
	}

	@Override
	public PagingResponse<MessageResponse> ticketMessageReadSelf(TicketMessageRequest request) {
		Page<Message> ticketPage = ticketMessageRepository.getByUserId(identity.getUserId(), request.toPageable());
		List<MessageResponse> ticketResponses = messageToMessageResponseMapper.map(ticketPage.getContent());
		final Paging<MessageResponse> posts = Paging.of(ticketPage, ticketResponses);
		return PagingResponse.<MessageResponse>builder().of(posts).content(messageToMessageResponseMapper.map(ticketPage.getContent())).build();
	}

	@Override
	public MessageResponse ticketMessageCreate(TicketMessageCreateRequest request) {
		Message message = Message.builder().userId(identity.getUserId()).ticketId(Long.valueOf(request.getTicketId())).message(request.getMessage()).build();
		Message saveTicket = this.ticketMessageRepository.save(message);
		return messageToMessageResponseMapper.map(saveTicket);
	}
}
