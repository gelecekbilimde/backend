package org.gelecekbilimde.scienceplatform.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;

import java.time.LocalDateTime;

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
			.responseCode(RandomUtil.generateUUID())
			.build();
	}


	public static <T> Response<T> create(final T response) {
		return Response.<T>builder()
			.list(response)
			.responseCode(RandomUtil.generateUUID())
			.build();
	}
}
