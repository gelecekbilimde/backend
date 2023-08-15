package org.gelecekbilimde.scienceplatform.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class Paging<R> {

	private List<R> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPageCount;
	private Long totalElementCount;

	public static <E, C> Paging<C> of(final Page<E> pageableEntities, final List<C> content) {

		final var responseBuilder = Paging.<C>builder()
			.content(content)
			.pageNumber(pageableEntities.getNumber() + 1)
			.pageSize(content.size())
			.totalPageCount(pageableEntities.getTotalPages())
			.totalElementCount(pageableEntities.getTotalElements());

		return responseBuilder.build();
	}

}
