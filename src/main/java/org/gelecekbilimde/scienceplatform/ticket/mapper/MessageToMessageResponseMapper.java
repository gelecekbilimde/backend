package org.gelecekbilimde.scienceplatform.ticket.mapper;

import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.MessageResponse;
import org.gelecekbilimde.scienceplatform.ticket.dto.response.TicketResponse;
import org.gelecekbilimde.scienceplatform.ticket.model.Message;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageToMessageResponseMapper extends BaseMapper<Message, MessageResponse> {
	static MessageToMessageResponseMapper initialize() {
		return Mappers.getMapper(MessageToMessageResponseMapper.class);
	}
}
