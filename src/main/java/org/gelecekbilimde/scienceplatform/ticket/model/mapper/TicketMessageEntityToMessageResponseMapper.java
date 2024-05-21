package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketMessageEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketMessageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketMessageEntityToMessageResponseMapper extends BaseMapper<TicketMessageEntity, TicketMessageResponse> {

	static TicketMessageEntityToMessageResponseMapper initialize() {
		return Mappers.getMapper(TicketMessageEntityToMessageResponseMapper.class);
	}

}
