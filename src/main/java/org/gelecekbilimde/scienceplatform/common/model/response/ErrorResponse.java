package org.gelecekbilimde.scienceplatform.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Builder
public class ErrorResponse {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    private String header;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @Builder.Default
    private final Boolean isSuccess = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SubError> subErrors;

    @Getter
    @Builder
    public static class SubError {

        private String message;

        private String field;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object value;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String type;

    }


    @Getter
    @RequiredArgsConstructor
    public enum Header {

        API_ERROR("API ERROR"),
        CONFLICT_ERROR("CONFLICT ERROR"),
        NOT_FOUND_ERROR("NOT FOUND ERROR"),
        VALIDATION_ERROR("VALIDATION ERROR"),
        DATABASE_ERROR("DATABASE ERROR"),
        PROCESS_ERROR("PROCESS ERROR"),
        BAD_REQUEST_ERROR("BAD REQUEST ERROR"),
        AUTH_ERROR("AUTH ERROR");

        private final String name;
    }


    public static ErrorResponseBuilder subErrors(final List<FieldError> fieldErrors) {

        if (CollectionUtils.isEmpty(fieldErrors)) {
            return ErrorResponse.builder();
        }

        final List<SubError> subErrorErrors = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            final SubError.SubErrorBuilder subErrorBuilder = SubError.builder();

            List<String> codes = List.of(Objects.requireNonNull(fieldError.getCodes()));
            if (!codes.isEmpty()) {

                subErrorBuilder.field(StringUtils.substringAfterLast(codes.get(0), "."));

                if (!"AssertTrue".equals(codes.get(codes.size() - 1))) {
                    subErrorBuilder.type(StringUtils.substringAfterLast(codes.get(codes.size() - 2), ".").replace('$', '.'));
                }
            }
            subErrorBuilder.value(fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null);
            subErrorBuilder.message(fieldError.getDefaultMessage());

            subErrorErrors.add(subErrorBuilder.build());
        });

        return ErrorResponse.builder().subErrors(subErrorErrors);
    }

    public static ErrorResponseBuilder subErrors(final Set<ConstraintViolation<?>> constraintViolations) {

        if (CollectionUtils.isEmpty(constraintViolations)) {
            return ErrorResponse.builder();
        }

        final List<SubError> subErrors = new ArrayList<>();

        constraintViolations.forEach(constraintViolation ->
                subErrors.add(
                        SubError.builder()
                                .message(constraintViolation.getMessage())
                                .field(StringUtils.substringAfterLast(constraintViolation.getPropertyPath().toString(), "."))
                                .value(constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString() : null)
                                .type(constraintViolation.getInvalidValue().getClass().getSimpleName()).build()
                )
        );

        return ErrorResponse.builder().subErrors(subErrors);
    }

    public static ErrorResponseBuilder subErrors(final MethodArgumentTypeMismatchException exception) {
        return ErrorResponse.builder()
                .subErrors(List.of(
                        SubError.builder()
                                .message(exception.getMessage().split(";")[0])
                                .field(exception.getName())
                                .value(Objects.requireNonNull(exception.getValue()).toString())
                                .type(Objects.requireNonNull(exception.getRequiredType()).getSimpleName()).build()
                ));
    }

    public static ErrorResponseBuilder subErrors(final InvalidFormatException exception) {

        return ErrorResponse.builder()
                .subErrors(List.of(
                        SubError.builder()
                                .message("must be accepted value")
                                .field(
                                        Optional.of(exception.getPath())
                                                .filter(path -> path.size() > 1)
                                                .map(path -> Optional.ofNullable(path.get(path.size() - 1).getFieldName())
                                                        .orElse(path.get(path.size() - 2).getFieldName()))
                                                .orElse(exception.getPath().get(0).getFieldName())
                                )
                                .value(exception.getValue())
                                .type(StringUtils.substringAfterLast(exception.getTargetType().getName(), ".").replace('$', '.'))
                                .build()
                ));
    }

}
