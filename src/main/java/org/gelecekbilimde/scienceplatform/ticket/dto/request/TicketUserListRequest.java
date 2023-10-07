package org.gelecekbilimde.scienceplatform.ticket.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class TicketUserListRequest {

	@NotNull
	private Integer userId;

	@NotNull
	@Range(min = 1)
	public Long page;

	@NotNull
	@Range(min = 2, max = 100)
	public Long limit;

	public Pageable toPageable() {
		return PageRequest.of(
			Math.toIntExact(page),
			Math.toIntExact(limit)
		);
	}
}
