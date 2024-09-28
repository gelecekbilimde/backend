package org.gelecekbilimde.scienceplatform.common.model.response;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ResponseBuilder {

	public static final SuccessResponse<Void> SUCCESS = SuccessResponse.<Void>builder()
		.isSuccess(true)
		.build();

	public static <T> SuccessResponse<T> successOf(final T response) {
		return SuccessResponse.<T>builder()
			.isSuccess(true)
			.content(response)
			.build();
	}

	public static <T> SuccessResponse<PagingResponse<T>> success() {
		return SuccessResponse.<PagingResponse<T>>builder()
			.isSuccess(true)
			.build();
	}

	public static <T> SuccessResponse<List<T>> successList() {
		return SuccessResponse.<List<T>>builder()
			.isSuccess(true)
			.build();
	}

}
