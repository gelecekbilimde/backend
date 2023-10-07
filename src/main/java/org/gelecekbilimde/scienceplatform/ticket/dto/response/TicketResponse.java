package org.gelecekbilimde.scienceplatform.ticket.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
public class TicketResponse {

	private Long id;
	private Long userId;
	private String message;
	private TicketStatus status;
	private LocalDateTime updateAt;
	private LocalDateTime createAt;

}
