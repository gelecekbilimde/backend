package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketCategory;

@Getter
@Setter
@Builder
public class TicketMessageCreateRequest {

	@NotNull
	private Long id;

	@NotNull
	private String message;

	@Enumerated(EnumType.STRING)
	private TicketCategory subject;

}
