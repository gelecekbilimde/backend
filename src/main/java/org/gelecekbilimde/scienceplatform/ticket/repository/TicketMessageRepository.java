package org.gelecekbilimde.scienceplatform.ticket.repository;

import org.gelecekbilimde.scienceplatform.ticket.model.TicketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketMessageRepository extends JpaRepository<TicketMessage, Integer>, JpaSpecificationExecutor<TicketMessage> {
}
