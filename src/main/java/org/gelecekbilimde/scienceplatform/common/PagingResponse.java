package org.gelecekbilimde.scienceplatform.common;

import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Getter
@Builder
public class PagingResponse<R> {

	private List<R> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalPageCount;
	private Long totalElementCount;


	@SuppressWarnings("unused")
	public static class PagingResponseBuilder<R> {
		public <M> PagingResponse.PagingResponseBuilder<R> of(final Paging<M> page) {
			return PagingResponse.<R>builder()
				.pageNumber(page.getPageNumber())
				.pageSize(page.getPageSize())
				.totalPageCount(page.getTotalPageCount())
				.totalElementCount(page.getTotalElementCount());
		}
	}
}

