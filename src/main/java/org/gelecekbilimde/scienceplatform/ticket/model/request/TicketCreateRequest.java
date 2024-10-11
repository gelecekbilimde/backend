package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketCategory;

@Getter
@Setter
@Builder
public class TicketCreateRequest {

	@NotNull
	private TicketCategory category;

	@NotBlank
	private String description;

}
