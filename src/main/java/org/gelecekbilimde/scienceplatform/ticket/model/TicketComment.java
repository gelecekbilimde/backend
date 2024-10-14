package org.gelecekbilimde.scienceplatform.ticket.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TicketComment extends BaseDomainModel {

	private Long id;
	private String userId;
	private Long ticketId;
	private String content;

}
