package org.gelecekbilimde.scienceplatform.ticket.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class TicketResponse {

	private Long id;
	private String userId;
	private String message;
	private TicketStatus status;
	private LocalDateTime updateAt;
	private LocalDateTime createAt;

}
