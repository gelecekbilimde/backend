package org.gelecekbilimde.scienceplatform.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;

import java.time.LocalDateTime;

@Getter
@Builder
public class GenericResponse<T> {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    @Builder.Default
    private String code = RandomUtil.generateUUID();

    @Builder.Default
    private Boolean isSuccess = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T content;


    public static GenericResponse<Void> success() {
        return GenericResponse.<Void>builder()
                .build();
    }

    public static <T> GenericResponse<T> success(final T content) {
        return GenericResponse.<T>builder()
                .content(content)
                .build();
    }

}
