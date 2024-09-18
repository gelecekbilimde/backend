package org.gelecekbilimde.scienceplatform.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;

import java.time.LocalDateTime;

@Getter
@Builder
public class SuccessResponse<T> {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    @Builder.Default
    private String code = RandomUtil.generateUUID();

    @Builder.Default
    private Boolean isSuccess = true;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T content;


	public static SuccessResponse<Void> success() {
		return SuccessResponse.<Void>builder()
			.build();
	}

	public static <T> SuccessResponse<T> success(final T content) {
		return SuccessResponse.<T>builder()
			.content(content)
			.build();
	}

}
