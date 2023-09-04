package org.gelecekbilimde.scienceplatform.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Builder
public class Response<T> {

	private final HttpStatus statusText;
	private final Integer status;


	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T list;

	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();


	private String responseCode;

	public static final Response<Void> NO_CONTENT = Response.<Void>builder()
		.statusText(HttpStatus.NO_CONTENT)
		.status(HttpStatus.NO_CONTENT.value())
		.build();

	public static <T> Response<T> ok(final T response) {
		return Response.<T>builder()
			.statusText(HttpStatus.OK)
			.status(HttpStatus.OK.value())
			.list(response)
			.responseCode(UUID.randomUUID().toString())
			.build();
	}


	public static <T> Response<T> create(final T response) {
		return Response.<T>builder()
			.statusText(HttpStatus.CREATED)
			.status(HttpStatus.CREATED.value())
			.list(response)
			.responseCode(UUID.randomUUID().toString())
			.build();
	}
}
