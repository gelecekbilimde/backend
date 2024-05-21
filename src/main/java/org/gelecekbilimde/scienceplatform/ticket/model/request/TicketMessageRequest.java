package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

@Getter
@Setter
public class TicketMessageRequest extends PagingRequest {

	@NotNull
	private Integer id;

}
