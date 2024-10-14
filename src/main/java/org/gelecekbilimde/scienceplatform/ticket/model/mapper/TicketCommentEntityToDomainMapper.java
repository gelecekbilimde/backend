package org.gelecekbilimde.scienceplatform.ticket.model.mapper;

import org.gelecekbilimde.scienceplatform.common.model.mapper.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketComment;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketCommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketCommentEntityToDomainMapper extends BaseMapper<TicketCommentEntity, TicketComment> {

	static TicketCommentEntityToDomainMapper initialize() {
		return Mappers.getMapper(TicketCommentEntityToDomainMapper.class);
	}

}
