package org.gelecekbilimde.scienceplatform.ticket.repository;

import org.gelecekbilimde.scienceplatform.ticket.model.TicketMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketMessageRepository extends JpaRepository<TicketMessage, Integer> {

	Page<TicketMessage> getByTicketId(Integer ticketId, Pageable pageable);

	Page<TicketMessage> getByUserId(String userId, Pageable pageable);

}
