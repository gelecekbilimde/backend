package org.gelecekbilimde.scienceplatform.ticket.port.adapter;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BasePageable;
import org.gelecekbilimde.scienceplatform.ticket.model.Ticket;
import org.gelecekbilimde.scienceplatform.ticket.model.TicketFilter;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketEntityToTicketMapper;
import org.gelecekbilimde.scienceplatform.ticket.model.mapper.TicketToEntityMapper;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketReadPort;
import org.gelecekbilimde.scienceplatform.ticket.port.TicketSavePort;
import org.gelecekbilimde.scienceplatform.ticket.repository.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class TicketAdapter implements TicketReadPort, TicketSavePort {

	private final TicketRepository ticketRepository;


	private final TicketToEntityMapper ticketToEntityMapper = TicketToEntityMapper.initialize();
	private final TicketEntityToTicketMapper ticketEntityToDomainMapper = TicketEntityToTicketMapper.initialize();


	@Override
	public BasePage<Ticket> findAll(final BasePageable basePageable,
									final TicketFilter filter) {

		final Pageable pageable = basePageable.toPageable();

		final Specification<TicketEntity> specification = Optional
			.ofNullable(filter)
			.map(TicketFilter::toSpecification)
			.orElse(Specification.allOf());

		final Page<TicketEntity> ticketEntitiesPage = ticketRepository
			.findAll(specification, pageable);

		final List<Ticket> tickets = ticketEntityToDomainMapper
			.map(ticketEntitiesPage.getContent());

		return BasePage.of(
			filter,
			ticketEntitiesPage,
			tickets
		);
	}


	@Override
	public Optional<Ticket> findById(Long id) {
		return ticketRepository.findById(id)
			.map(ticketEntityToDomainMapper::map);
	}


	@Override
	public void save(Ticket ticket) {
		final TicketEntity ticketEntity = ticketToEntityMapper.map(ticket);
		ticketRepository.save(ticketEntity);
	}

}
