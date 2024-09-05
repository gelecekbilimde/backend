package org.gelecekbilimde.scienceplatform.ticket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketStatus;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Ticket extends BaseDomainModel {

	private Long id;
	private String userId;
	private String message;
	private TicketStatus status;

}
