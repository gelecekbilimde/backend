package org.gelecekbilimde.scienceplatform.ticket.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;

import java.util.Set;

@Getter
@Setter
public class TicketMessageRequest extends PagingRequest {

	@NotNull
	private Integer id;

	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of();
		return this.isPropertyAccepted(acceptedFilterFields);
	}
}
