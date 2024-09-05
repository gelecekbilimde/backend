package org.gelecekbilimde.scienceplatform.ticket.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class TicketMessageResponse {

	private Long id;
	private Long userId;
	private Long ticketId;
	private String message;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
