package org.gelecekbilimde.scienceplatform.ticket.repository;

import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketCommentRepository extends JpaRepository<TicketCommentEntity, Long> {

	List<TicketCommentEntity> findAllByTicketId(Long ticketId);

}
