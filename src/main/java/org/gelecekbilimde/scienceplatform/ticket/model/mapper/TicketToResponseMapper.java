package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketToResponseMapper extends BaseMapper<Ticket, TicketResponse> {

	static TicketToResponseMapper initialize() {
		return Mappers.getMapper(TicketToResponseMapper.class);
	}

}
