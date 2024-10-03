package org.gelecekbilimde.scienceplatform.post.model;

import lombok.Getter;
import lombok.Setter;
import org.gelecekbilimde.scienceplatform.common.model.BaseFilter;
import org.gelecekbilimde.scienceplatform.post.model.entity.PostEntity;
import org.springframework.data.jpa.domain.Specification;


@Getter
@Setter
public class AdminPostFilter implements BaseFilter {

	private Boolean isActive;

	@Override
	public Specification<PostEntity> toSpecification() {
		Specification<PostEntity> specification = Specification.where(null);

		if (isActive != null) {
			specification = specification.and((root, query, criteriaBuilder) ->
				criteriaBuilder.equal(root.get("isActive"), isActive)
			);
		}
		return specification;
	}
}
