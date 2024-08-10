package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketEntityToTicketMapper extends BaseMapper<TicketEntity, Ticket> {

	static TicketEntityToTicketMapper initialize() {
		return Mappers.getMapper(TicketEntityToTicketMapper.class);
	}

}
