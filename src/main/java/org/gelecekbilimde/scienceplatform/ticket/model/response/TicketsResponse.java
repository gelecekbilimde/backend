package org.gelecekbilimde.scienceplatform.ticket.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketCategory;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class TicketsResponse {

	private Long id;
	private String userId;
	private TicketCategory category;
	private String title;
	private TicketStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
