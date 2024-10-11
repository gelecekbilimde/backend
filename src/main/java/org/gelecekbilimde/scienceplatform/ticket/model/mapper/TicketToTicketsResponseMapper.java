package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketToTicketsResponseMapper extends BaseMapper<Ticket, TicketsResponse> {

	static TicketToTicketsResponseMapper initialize() {
		return Mappers.getMapper(TicketToTicketsResponseMapper.class);
	}

}
