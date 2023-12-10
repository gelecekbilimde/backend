package org.gelecekbilimde.scienceplatform.ticket.repository;

import org.gelecekbilimde.scienceplatform.ticket.model.Message;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketMessageRepository extends JpaRepository<Message, Integer> {

	Page<Message> getByTicketId(Integer ticketId, Pageable pageable);

}
