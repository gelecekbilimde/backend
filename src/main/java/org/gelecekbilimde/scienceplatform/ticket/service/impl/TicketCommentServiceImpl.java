package org.gelecekbilimde.scienceplatform.ticket.service.impl;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.auth.model.Identity;
import org.gelecekbilimde.scienceplatform.ticket.exception.TicketNotFoundByIdException;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;
import org.gelecekbilimde.scienceplatform.ticket.model.request.TicketCommentAddRequest;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketCommentReadPort;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketCommentSavePort;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketReadPort;
import org.gelecekbilimde.scienceplatform.ticket.service.TicketCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class TicketCommentServiceImpl implements TicketCommentService {

	private final TicketCommentReadPort ticketCommentReadPort;
	private final TicketCommentSavePort ticketCommentSavePort;
	private final TicketReadPort ticketReadPort;
	private final Identity identity;


	@Override
	public List<TicketComment> findAllByTicketId(final Long ticketId) {

		this.validateTicketIdAndCurrentUserPermission(ticketId);

		return ticketCommentReadPort.findAllByTicketId(ticketId);
	}

	@Override
	public void add(final Long ticketId, final TicketCommentAddRequest request) {

		this.validateTicketIdAndCurrentUserPermission(ticketId);

		final TicketComment ticketComment = TicketComment.builder()
			.ticketId(ticketId)
			.userId(identity.getUserId())
			.content(request.getContent())
			.build();
		ticketCommentSavePort.save(ticketComment);
	}

	private void validateTicketIdAndCurrentUserPermission(final Long ticketId) {

		final Ticket ticket = ticketReadPort.findById(ticketId)
			.orElseThrow(() -> new TicketNotFoundByIdException(ticketId));

		boolean isNotAdmin = !identity.isAdmin();
		boolean isNotOwner = !ticket.getUserId().equals(identity.getUserId());
		if (isNotAdmin && isNotOwner) {
			throw new TicketNotFoundByIdException(ticketId);
		}
	}

}
