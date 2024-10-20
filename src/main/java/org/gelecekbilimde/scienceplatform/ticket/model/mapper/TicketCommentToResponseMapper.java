package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;
import org.gelecekbilimde.scienceplatform.ticket.model.response.TicketCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketCommentToResponseMapper extends BaseMapper<TicketComment, TicketCommentResponse> {

	static TicketCommentToResponseMapper initialize() {
		return Mappers.getMapper(TicketCommentToResponseMapper.class);
	}

}
