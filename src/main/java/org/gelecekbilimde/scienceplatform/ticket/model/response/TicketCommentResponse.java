package org.gelecekbilimde.scienceplatform.ticket.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketCommentResponse {

	private Long id;
	private String userId;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
