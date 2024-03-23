package org.gelecekbilimde.scienceplatform.common;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Data
public class PagingRequest {

	@NotNull
	@Range(min = 1)
	public Integer page = 1;

	@NotNull
	@Range(min = 2, max = 100)
	public Integer pageSize = 20;


	public Pageable toPageable() {
		return PageRequest.of(
			this.getPage(),
			this.getPageSize()
		);
	}

	public Integer getPage() {
		return this.page - 1;
	}
}
