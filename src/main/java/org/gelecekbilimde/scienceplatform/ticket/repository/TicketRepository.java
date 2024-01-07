package org.gelecekbilimde.scienceplatform.ticket.repository;

import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	Page<Ticket> getByUserId(String userId, Pageable pageable);

}
