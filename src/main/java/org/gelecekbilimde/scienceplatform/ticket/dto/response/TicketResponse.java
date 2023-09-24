package org.gelecekbilimde.scienceplatform.ticket.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatusEnum;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class TicketResponse {

	private Long id;
	private Long userId;
	private String message;
	private TicketStatusEnum status;
	private LocalDateTime updateAt;
	private LocalDateTime createAt;

}
