package org.gelecekbilimde.scienceplatform.ticket.repository;

import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TicketMessageRepository extends JpaRepository<TicketMessageEntity, Long>, JpaSpecificationExecutor<TicketMessageEntity> {
}
