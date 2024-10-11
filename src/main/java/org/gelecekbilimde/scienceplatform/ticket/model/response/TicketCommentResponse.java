package org.gelecekbilimde.scienceplatform.ticket.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class TicketCommentResponse {

	private Long id;
	private String userId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
