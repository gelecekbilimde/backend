package org.gelecekbilimde.scienceplatform.ticket.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketCommentEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketCommentEntityToDomainMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketCommentToEntityMapper;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketCommentReadPort;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketCommentSavePort;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketCommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class TicketCommentAdapter implements TicketCommentReadPort, TicketCommentSavePort {

	private final TicketCommentRepository ticketCommentRepository;


	private final TicketCommentToEntityMapper ticketCommentToEntityMapper = TicketCommentToEntityMapper.initialize();
	private final TicketCommentEntityToDomainMapper ticketCommentEntityToDomainMapper = TicketCommentEntityToDomainMapper.initialize();


	@Override
	public List<TicketComment> findAllByTicketId(final Long ticketId) {
		final List<TicketCommentEntity> ticketCommentEntities = ticketCommentRepository.findAllByTicketId(ticketId);
		return ticketCommentEntityToDomainMapper.map(ticketCommentEntities);
	}


	@Override
	public void save(final TicketComment ticketComment) {
		final TicketCommentEntity ticketCommentEntity = ticketCommentToEntityMapper.map(ticketComment);
		ticketCommentRepository.save(ticketCommentEntity);
	}

}
