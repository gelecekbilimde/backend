package org.gelecekbilimde.scienceplatform.ticket.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.request.PagingRequest;
import org.gelecekbilimde.scienceplatform.ticket.TicketFilter;

import java.util.Set;

@Getter
@Setter
public class TicketListRequest extends PagingRequest {

	@Valid
	private TicketFilter filter;


	@JsonIgnore
	@AssertTrue
	@Override
	public boolean isOrderPropertyAccepted() {
		final Set<String> acceptedFilterFields = Set.of("createdAt");
		return this.isPropertyAccepted(acceptedFilterFields);
	}

}
