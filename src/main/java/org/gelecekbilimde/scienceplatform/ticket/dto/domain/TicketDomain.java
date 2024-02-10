package org.gelecekbilimde.scienceplatform.ticket.dto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class TicketDomain {

	private Long id;
	private String userId;
	private String message;
	private TicketStatus status;
	private LocalDateTime updateAt;
	private LocalDateTime createAt;

}
