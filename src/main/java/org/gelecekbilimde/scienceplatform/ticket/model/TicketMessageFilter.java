package org.gelecekbilimde.scienceplatform.ticket.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.BaseFilter;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketEntity;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class TicketMessageFilter implements BaseFilter {

	private Integer id;

	@Override
	public Specification<TicketEntity> toSpecification() {

		Specification<TicketEntity> specification = Specification.where(null);
		if (id != null) {
			specification = specification.and((root, query, criteriaBuilder) ->
				criteriaBuilder.equal(root.get("id"), id)
			);
		}
		return specification;
	}
}
