package org.gelecekbilimde.scienceplatform.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
public class BasePage<R> {

	private List<R> content;

	private Integer pageNumber;

	private Integer pageSize;

	private Integer totalPageCount;

	private Long totalElementCount;

	private List<BaseSort.BaseOrder> orderedBy;

	private BaseFilter filteredBy;


	public static <E, C> BasePage<C> of(final Page<E> pageableEntities,
										final List<C> content) {

		final var responseBuilder = BasePage.<C>builder()
			.content(content)
			.pageNumber(pageableEntities.getNumber() + 1)
			.pageSize(content.size())
			.totalPageCount(pageableEntities.getTotalPages())
			.totalElementCount(pageableEntities.getTotalElements());

		if (pageableEntities.getSort().isSorted()) {
			responseBuilder.orderedBy(BaseSort.of(pageableEntities.getSort()).getOrders());
		}

		return responseBuilder.build();
	}

	public static <E, C> BasePage<C> of(final BaseFilter filter,
										final Page<E> pageableEntities,
										final List<C> content) {

		final var responseBuilder = BasePage.<C>builder()
			.content(content)
			.pageNumber(pageableEntities.getNumber() + 1)
			.pageSize(content.size())
			.totalPageCount(pageableEntities.getTotalPages())
			.totalElementCount(pageableEntities.getTotalElements())
			.filteredBy(filter);

		if (pageableEntities.getSort().isSorted()) {
			responseBuilder.orderedBy(BaseSort.of(pageableEntities.getSort()).getOrders());
		}

		return responseBuilder.build();
	}
}
