package org.gelecekbilimde.scienceplatform.ticket.repository;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatusEnum;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Ticket e SET e.status = :newStatus WHERE e.id = :ticketId")
	int updateStatusById(@Param("ticketId") Integer ticketId, @Param("newStatus") TicketStatusEnum newStatus);

	Page<Ticket> getByUserId(Integer userId, Pageable pageable);

}
