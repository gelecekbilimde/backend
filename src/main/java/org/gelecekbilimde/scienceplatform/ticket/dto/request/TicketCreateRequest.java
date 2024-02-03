package org.gelecekbilimde.scienceplatform.ticket.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
