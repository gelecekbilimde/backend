package org.gelecekbilimde.scienceplatform.auth.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.auth.model.entity.RoleApplicationEntity;
import org.gelecekbilimde.scienceplatform.auth.model.enums.RoleApplicationStatus;
import org.gelecekbilimde.scienceplatform.common.model.BaseFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Getter
@Setter
@Builder
public class RoleApplicationFilter implements BaseFilter {

	private Set<RoleApplicationStatus> statuses;


	@Override
	public Specification<RoleApplicationEntity> toSpecification() {

		Specification<RoleApplicationEntity> specification = Specification.where(null);

		if (!CollectionUtils.isEmpty(this.statuses)) {

			Specification<RoleApplicationEntity> statusSpecification = this.statuses.stream()
				.map(status -> (Specification<RoleApplicationEntity>) (root, query, criteriaBuilder) ->
					criteriaBuilder.equal(root.get("status"), status))
				.reduce(Specification::or)
				.orElse(null);

			specification = specification.and(statusSpecification);
		}

		return specification;
	}

}
