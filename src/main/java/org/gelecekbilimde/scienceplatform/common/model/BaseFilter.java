package org.gelecekbilimde.scienceplatform.common.model;

import org.springframework.data.jpa.domain.Specification;

public interface BaseFilter {

	@SuppressWarnings({"java:S3740", "rawtypes"})
	Specification toSpecification();

}
