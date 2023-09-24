package org.gelecekbilimde.scienceplatform.ticket.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreateRequest {

	@NotNull(message = "cannot be null")
	private Long userId;

	@NotNull(message = "cannot be null")
	private String message;

}
