package org.gelecekbilimde.scienceplatform.ticket.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gelecekbilimde.scienceplatform.ticket.enums.TicketStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketUpdateRequest {

	@NotNull(message = "cannot be null")
    private Integer ticketId;

	@Enumerated(EnumType.STRING)
	private TicketStatusEnum status;


}
