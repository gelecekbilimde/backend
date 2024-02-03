package org.gelecekbilimde.scienceplatform.ticket.mapper;


import org.gelecekbilimde.scienceplatform.common.BaseMapper;
import org.gelecekbilimde.scienceplatform.ticket.dto.domain.TicketDomain;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TicketModelToTicketDomainMapper extends BaseMapper<Ticket, TicketDomain> {
	static TicketModelToTicketDomainMapper initialize() {
		return Mappers.getMapper(TicketModelToTicketDomainMapper.class);
	}
}

