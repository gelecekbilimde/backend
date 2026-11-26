package org.gelecekbilimde.scienceplatform.ticket.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

import java.util.EnumSet;

@Getter
@Setter
public class TicketUpdateRequest {

	@NotNull
	private TicketStatus status;


	@JsonIgnore
	@AssertTrue
	@SuppressWarnings("This method is unused by the application directly but Spring is using it in the background.")
	private boolean isStatusAccepted() {

		if (this.status == null) {
			return true;
		}

		final EnumSet<TicketStatus> acceptedStatuses = EnumSet.of(
			TicketStatus.IN_PROGRESS,
			TicketStatus.ON_HOLD,
			TicketStatus.CLOSED,
			TicketStatus.REOPENED,
			TicketStatus.CANCELED,
			TicketStatus.RESOLVED
		);
		return acceptedStatuses.contains(this.status);
	}

}
