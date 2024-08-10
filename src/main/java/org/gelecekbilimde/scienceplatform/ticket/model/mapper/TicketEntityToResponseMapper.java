package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketEntityToResponseMapper extends BaseMapper<TicketEntity, TicketResponse> {

	static TicketEntityToResponseMapper initialize() {
		return Mappers.getMapper(TicketEntityToResponseMapper.class);
	}

}
