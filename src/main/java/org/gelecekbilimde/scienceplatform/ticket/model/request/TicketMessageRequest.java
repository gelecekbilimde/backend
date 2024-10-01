package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketMessageFilter;

import java.util.Set;

@Getter
@Setter
public class TicketMessageRequest extends PagingRequest {

	@NotNull
	private TicketMessageFilter filter;

	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of();
		return this.isPropertyAccepted(acceptedFilterFields);
	}
}
