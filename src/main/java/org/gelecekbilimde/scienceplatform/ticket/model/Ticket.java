package org.gelecekbilimde.scienceplatform.ticket.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class Ticket {

	private Long id;
	private String userId;
	private String message;
	private TicketStatus status;
	private LocalDateTime updateAt;
	private LocalDateTime createAt;

}
