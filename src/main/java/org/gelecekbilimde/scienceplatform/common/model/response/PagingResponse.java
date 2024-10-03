package org.gelecekbilimde.scienceplatform.common.model.response;

import lombok.Builder;
import lombok.Getter;
import org.gelecekbilimde.scienceplatform.common.model.BaseFilter;
import org.gelecekbilimde.scienceplatform.common.model.BasePage;
import org.gelecekbilimde.scienceplatform.common.model.BaseSort;

import java.util.List;

@Getter
@Builder
public class PagingResponse<R> {

	private List<R> content;

	private Integer pageNumber;

	private Integer pageSize;

	private Integer totalPageCount;

	private Long totalElementCount;

	private List<BaseSort.BaseOrder> orderedBy;

	private BaseFilter filteredBy;


	@SuppressWarnings("This method is unused by the application directly but Spring is using it in the background.")
	public static class PagingResponseBuilder<R> {

		public <M> PagingResponse.PagingResponseBuilder<R> of(final BasePage<M> page) {
			return PagingResponse.<R>builder()
				.pageNumber(page.getPageNumber())
				.pageSize(page.getPageSize())
				.totalPageCount(page.getTotalPageCount())
				.totalElementCount(page.getTotalElementCount())
				.orderedBy(page.getOrderedBy())
				.filteredBy(page.getFilteredBy());
		}
	}
}

