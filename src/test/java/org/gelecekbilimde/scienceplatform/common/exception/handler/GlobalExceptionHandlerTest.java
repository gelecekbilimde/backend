package org.gelecekbilimde.scienceplatform.common.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.dockerjava.api.exception.BadRequestException;
import com.github.dockerjava.api.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.RestControllerTest;
import org.gelecekbilimde.scienceplatform.common.exception.AbstractAuthException;
import org.gelecekbilimde.scienceplatform.common.exception.AbstractNotFoundException;
import org.gelecekbilimde.scienceplatform.common.exception.AbstractServerException;
import org.gelecekbilimde.scienceplatform.common.model.response.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.Serial;
import java.lang.reflect.Method;
import java.rmi.ServerException;
import java.sql.SQLException;

@Slf4j
class GlobalExceptionHandlerTest extends RestControllerTest {

	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;

	@Test
	@SuppressWarnings("ConstantConditions")
	void givenInvalidArgumentToAnyEndpoint_whenThrowMethodArgumentTypeMismatchException_thenReturnError() {
		// Given
		MethodArgumentTypeMismatchException mockException = new MethodArgumentTypeMismatchException("test", String.class, "username", null, null);

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.subErrors(mockException)
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleValidationErrors(mockException);
		this.checkError(mockErrorResponse, errorResponse);

	}

	@Test
	void givenInvalidArgumentToAnyEndpoint_whenThrowMethodArgumentNotValidException_thenReturnError() throws NoSuchMethodException {

		// Given
		MethodArgumentNotValidException mockException = this.getMethodArgumentNotValidException();

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.subErrors(mockException.getBindingResult().getFieldErrors())
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleValidationErrors(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	private @NotNull MethodArgumentNotValidException getMethodArgumentNotValidException() throws NoSuchMethodException {
		Method method = GlobalExceptionHandlerTest.class.getDeclaredMethod("givenInvalidArgumentToAnyEndpoint_whenThrowMethodArgumentNotValidException_thenReturnAysError");
		int parameterIndex = -1;

		MethodParameter mockParameter = new MethodParameter(method, parameterIndex);
		BindingResult mockBindingResult = new BeanPropertyBindingResult(null, "");
		return new MethodArgumentNotValidException(mockParameter, mockBindingResult);
	}

	@Test
	void givenInvalidArgumentToAnyEndpoint_whenThrowConstraintViolationException_thenReturnError() {

		// Given
		ConstraintViolationException mockException = new ConstraintViolationException(null);

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.subErrors(mockException.getConstraintViolations())
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handlePathVariableErrors(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenResourceNotFound_whenThrowNotExistException_thenReturnErrorWithReturnError() {

		// Given
		AbstractNotFoundException mockException = new AbstractNotFoundException("Resource not found") {

			@Serial
			private static final long serialVersionUID = 1L;

			@Override
			public String getMessage() {
				return "Resource not found";
			}
		};

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.NOT_FOUND_ERROR.getName())
			.message(mockException.getMessage())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleNotExistError(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenInternalServerException_whenThrowProcessException_WithReturnError() {

		// Given
		AbstractServerException mockException = new AbstractServerException("Internal server error") {

			@Serial
			private static final long serialVersionUID = 1L;

			@Override
			public String getMessage() {
				return "Internal server error";
			}
		};

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.PROCESS_ERROR.getName())
			.message(mockException.getMessage())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleProcessError(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenHandleEndpointNotFoundException_whenThrowNoResourceFoundException_thenReturnError() {

		// Given
		HttpMethod[] httpMethods = HttpMethod.values();

		for (HttpMethod method : httpMethods) {
			AbstractNotFoundException mockException = new AbstractNotFoundException("Resource not found: " + method) {
				@Override
				public String getMessage() {
					return super.getMessage();
				}
			};

			// When
			ErrorResponse mockErrorResponse = ErrorResponse.builder()
				.header(ErrorResponse.Header.API_ERROR.getName())
				.build();

			// Then
			ErrorResponse errorResponse = globalExceptionHandler.handleNotExistError(mockException);
			this.checkError(mockErrorResponse, errorResponse);
		}
	}

	@Test
	void givenAccessDeniedException_whenThrowAccessDeniedException_thenReturnError() {

		// Given
		AccessDeniedException mockException = new AccessDeniedException("Access denied");

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.AUTH_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleAccessDeniedError(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenSQLException_whenThrowSQLException_thenReturnAysError() {

		// Given
		SQLException mockException = new SQLException("Database error");

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.DATABASE_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleSQLError(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenAuthenticationFailure_whenThrowAysAuthException_thenReturnAysError() {
		// Given
		AbstractAuthException mockException = new AbstractAuthException("Authentication failed") {

			@Serial
			private static final long serialVersionUID = 1L;

			@Override
			public String getMessage() {
				return "Authentication failed";
			}
		};

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.AUTH_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleAuthError(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenUnsupportedHttpMethod_whenThrowHttpRequestMethodNotSupportedException_thenReturnAysError() {

		// Given
		HttpRequestMethodNotSupportedException mockException = new HttpRequestMethodNotSupportedException("Unsupported method");

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleHttpRequestMethodNotSupportedException(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenUnsupportedMediaType_whenThrowHttpMediaTypeNotSupportedException_thenReturnAysError() {

		// Given
		HttpMediaTypeNotSupportedException mockException = new HttpMediaTypeNotSupportedException("Unsupported media type");

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleHttpMediaTypeNotSupportedException(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenDataAccessException_whenThrowDataAccessException_thenReturnAysErrorWithInternalServerErrorStatus() {

		// Given
		DataAccessException mockException = new DataAccessException("Data access error") {

			@Serial
			private static final long serialVersionUID = 1L;
		};

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.DATABASE_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleDataAccessException(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenJsonParseError_whenThrowHttpMessageNotReadableException_thenReturnAysError() {

		// Given
		HttpInputMessage mockHttpInputMessage = new MockHttpInputMessage(new byte[]{});
		HttpMessageNotReadableException mockException = new HttpMessageNotReadableException("Invalid JSON", mockHttpInputMessage);

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleJsonParseErrors(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	@SuppressWarnings("deprecation")
	void givenInvalidJsonFormat_whenThrowHttpMessageNotReadableException_thenReturnErrorResponse() {

		// Given
		InvalidFormatException mockInvalidFormatException = InvalidFormatException.from(null, "Invalid format", null, String.class);
		JsonMappingException.Reference mockReference = new JsonMappingException.Reference("testObject", "testField");
		mockInvalidFormatException.prependPath(mockReference);
		HttpMessageNotReadableException mockException = new HttpMessageNotReadableException("Invalid JSON", mockInvalidFormatException);

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.subErrors(mockInvalidFormatException)
			.header(ErrorResponse.Header.VALIDATION_ERROR.getName())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleJsonParseErrors(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	@Test
	void givenInvalidStatus_whenThrowAysInvalidStatusException_thenReturnAysErrorWithReturnAysError() {

		// Given
		HttpMessageNotReadableException mockException = new HttpMessageNotReadableException("Bad request");

		// When
		ErrorResponse mockErrorResponse = ErrorResponse.builder()
			.header(ErrorResponse.Header.BAD_REQUEST_ERROR.getName())
			.message(mockException.getMessage())
			.build();

		// Then
		ErrorResponse errorResponse = globalExceptionHandler.handleJsonParseErrors(mockException);
		this.checkError(mockErrorResponse, errorResponse);
	}

	private void checkError(ErrorResponse mockErrorResponse, ErrorResponse errorResponse) {
		Assertions.assertNotNull(errorResponse.getTime());
		Assertions.assertEquals(mockErrorResponse.getHeader(), errorResponse.getHeader());
		Assertions.assertEquals(mockErrorResponse.getIsSuccess(), errorResponse.getIsSuccess());

		if (mockErrorResponse.getMessage() != null) {
			Assertions.assertEquals(mockErrorResponse.getMessage(), errorResponse.getMessage());
		}

		if (mockErrorResponse.getSubErrors() != null) {
			Assertions.assertEquals(mockErrorResponse.getSubErrors().size(), errorResponse.getSubErrors().size());
			Assertions.assertEquals(mockErrorResponse.getSubErrors().get(0).getMessage(), errorResponse.getSubErrors().get(0).getMessage());
			Assertions.assertEquals(mockErrorResponse.getSubErrors().get(0).getField(), errorResponse.getSubErrors().get(0).getField());
			Assertions.assertEquals(mockErrorResponse.getSubErrors().get(0).getValue(), errorResponse.getSubErrors().get(0).getValue());
			Assertions.assertEquals(mockErrorResponse.getSubErrors().get(0).getType(), errorResponse.getSubErrors().get(0).getType());
		}

	}

}
