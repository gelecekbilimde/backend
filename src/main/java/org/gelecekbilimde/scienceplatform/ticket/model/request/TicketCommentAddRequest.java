package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCommentAddRequest {

	@NotBlank
	private String content;

}
