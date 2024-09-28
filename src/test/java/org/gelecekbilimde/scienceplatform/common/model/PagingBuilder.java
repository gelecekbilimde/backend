package org.gelecekbilimde.scienceplatform.common.model;

public class PagingBuilder extends TestDataBuilder<Paging> {

	public PagingBuilder() {
		super(Paging.class);
	}

	public PagingBuilder withValidValues() {
		return this
			.withPage(1)
			.withPageSize(10);
	}

	public PagingBuilder withPage(int page) {
		data.setPageNumber(page);
		return this;
	}

	public PagingBuilder withPageSize(int pageSize) {
		data.setPageSize(pageSize);
		return this;
	}

}
