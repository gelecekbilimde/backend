package org.gelecekbilimde.scienceplatform.ticket.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
@Setter
@SuperBuilder
public class MessageResponse {

	private Long id;
	private Long userId;
	private Long ticketId;
	private String message;
	private LocalDateTime updateAt;
	private LocalDateTime createAt;

}
