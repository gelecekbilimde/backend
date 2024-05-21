package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

@Getter
@Setter
public class TicketUpdateRequest {

	@NotNull
	private Long id;

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

}
