package org.gelecekbilimde.scienceplatform.dto.Post.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gelecekbilimde.scienceplatform.model.enums.PostProcessEnum;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
@EqualsAndHashCode()
@Data
public class AdminPostListRequestDTO {


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
