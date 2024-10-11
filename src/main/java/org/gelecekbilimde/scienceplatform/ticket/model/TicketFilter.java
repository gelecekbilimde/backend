package org.gelecekbilimde.scienceplatform.ticket.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.BaseFilter;
import org.gelecekbilimde.scienceplatform.ticket.model.entity.TicketEntity;
import org.gelecekbilimde.scienceplatform.ticket.model.enums.TicketCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Getter
@Setter
public class TicketFilter implements BaseFilter {

	private Set<TicketCategory> categories;


	@Override
	public Specification<TicketEntity> toSpecification() {

		Specification<TicketEntity> specification = Specification.where(null);

		if (!CollectionUtils.isEmpty(this.categories)) {

			Specification<TicketEntity> categorySpecification = this.categories.stream()
				.map(
					category -> (Specification<TicketEntity>) (root, query, criteriaBuilder) ->
						criteriaBuilder.equal(root.get("category"), category)
				)
				.reduce(Specification::or)
				.orElse(null);

			specification = specification.and(categorySpecification);
		}

		return specification;
	}

}
