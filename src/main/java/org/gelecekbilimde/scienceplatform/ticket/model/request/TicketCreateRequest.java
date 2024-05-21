package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TicketCreateRequest {

	@NotBlank
	private String message;

}
