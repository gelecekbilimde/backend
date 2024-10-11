package org.gelecekbilimde.scienceplatform.ticket.model.response;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketCategory;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketResponse {

	private Long id;
	private String userId;
	private TicketCategory category;
	private String title;
	private String description;
	private TicketStatus status;
	private LocalDateTime createdAt;

}
