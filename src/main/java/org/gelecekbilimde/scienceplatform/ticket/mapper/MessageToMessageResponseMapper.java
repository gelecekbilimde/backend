package org.gelecekbilimde.scienceplatform.ticket.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.MessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageToMessageResponseMapper extends BaseMapper<TicketMessage, MessageResponse> {
	static MessageToMessageResponseMapper initialize() {
		return Mappers.getMapper(MessageToMessageResponseMapper.class);
	}
}
