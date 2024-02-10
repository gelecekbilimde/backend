package org.gelecekbilimde.scienceplatform.ticket.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketToTicketResponseMapper extends BaseMapper<Ticket, TicketResponse> {
	static TicketToTicketResponseMapper initialize() {
		return Mappers.getMapper(TicketToTicketResponseMapper.class);
	}
}
