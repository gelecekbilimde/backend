package org.gelecekbilimde.scienceplatform.ticket.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatus;

@Getter
@Setter
public class TicketUpdateRequest {

	@NotNull
	private Integer id;

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

}
