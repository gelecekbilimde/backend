package org.gelecekbilimde.scienceplatform.ticket.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class TicketRequest {


	@NotNull
	@Range(min = 1)
	private Integer page;

	@NotNull
	@Range(min = 10, max = 100)
	private Integer pageSize;

	public Pageable toPageable() {
		return PageRequest.of(this.page - 1, pageSize);
	}

}
