package org.gelecekbilimde.scienceplatform.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class Response<T> {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T list;

	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();


	private String responseCode;

	public static final Response<Void> NO_CONTENT = Response.<Void>builder()
		.build();

	public static <T> Response<T> ok(final T response) {
		return Response.<T>builder()
			.list(response)
			.responseCode(UUID.randomUUID().toString())
			.build();
	}


	public static <T> Response<T> create(final T response) {
		return Response.<T>builder()
			.list(response)
			.responseCode(UUID.randomUUID().toString())
			.build();
	}
}
