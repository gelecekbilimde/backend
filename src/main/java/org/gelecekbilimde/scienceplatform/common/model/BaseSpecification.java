package org.gelecekbilimde.scienceplatform.common.model;

import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;


@Getter
@Builder
@UtilityClass
public class BaseSpecification {
	@SuppressWarnings("unused")
	public static class BaseSpecificationBuilder<C> {

		public Specification<C> and(final Map<String, Object> filter) {
			final Predicate[] predicates = new Predicate[filter.size()];
			final String[] names = filter.keySet().toArray(new String[0]);

			return ((root, query, criteriaBuilder) -> {

				for (int count = 0; count < filter.size(); count++) {

					final String name = names[count];
					final Object value = filter.get(name);

					predicates[count] = criteriaBuilder.equal(root.get(name), value);
				}
				return criteriaBuilder.and(predicates);
			});
		}
	}
}
