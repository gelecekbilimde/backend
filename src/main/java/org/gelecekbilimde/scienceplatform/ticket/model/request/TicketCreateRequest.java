package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@Size(min = 2, max = 512)
	private String title;

	@NotBlank
	private String description;

}
