package org.gelecekbilimde.scienceplatform.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.auth.exception.UserNotFoundByEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {

	private static final String ERROR = "error";
	private static final String WARN = "warn";
	private static final String INFO = "info";

	@ExceptionHandler(value = {ClientException.class})
	public ResponseEntity<Object> handleClientException(ClientException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = e.getMessage();

		return trowException(request, status, message, INFO, new HashMap<>());
	}

	@ExceptionHandler(value = {AbstractConflictException.class})
	public ResponseEntity<Object> handleConflictException(AbstractConflictException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		String message = e.getMessage();

		return trowException(request, status, message, WARN, new HashMap<>());
	}

	@ExceptionHandler(value = {AbstractNotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(AbstractNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = e.getMessage();

		return trowException(request, status, message, INFO, new HashMap<>());
	}

	@ExceptionHandler(value = {AbstractAuthException.class})
	public ResponseEntity<Object> handleAuthException(AbstractAuthException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		return trowException(request, status, message, WARN, new HashMap<>());
	}

	@ExceptionHandler(value = {ServerException.class})
	public ResponseEntity<Object> handleServerException(ServerException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return trowException(request, status, message, ERROR, new HashMap<>());
	}

	@ExceptionHandler(value = {UserNotFoundByEmailException.class})
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundByEmailException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		return trowException(request, status, message, ERROR, new HashMap<>());
	}

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "Doğrulama Hatası";
		HashMap<String, String> validationMessage = new HashMap<>();

		for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
			validationMessage.put(error.getObjectName(), error.getDefaultMessage());
		}

		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			validationMessage.put(error.getField(), error.getDefaultMessage());
		}

		return trowException(request, status, message, ERROR, validationMessage);
	}


	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleArgumentTypeMisMatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		String paramName = e.getName();
		Class<?> requiredType = e.getRequiredType();
		String invalidValue = e.getValue() != null ? e.getValue().toString() : "null"; // TODO: Burada NullPointerException alınabilir
		String expectedType = requiredType != null ? requiredType.getSimpleName() : "unknown";

		StringJoiner enumList = new StringJoiner(", ");
		if (requiredType != null) {
			Object[] its = requiredType.getEnumConstants();
			if (its != null) {
				for (Object it : its) {
					enumList.add(it.toString());
				}
			}
		}

		String message = "Parameter error: " + paramName + ". " +
			"Expected type: " + expectedType + ". " +
			"Invalid value: " + invalidValue + ". ";

		if (!enumList.toString().isEmpty()) {
			message += "The parameter must be one of the following values: " + enumList;
		}

		return trowException(request, status, message, ERROR, new HashMap<>());
	}


	private ResponseEntity<Object> trowException(HttpServletRequest request, HttpStatus status, String message, String logLevel, @Nullable Map<String, String> validationMessage) {

		String path = request.getRequestURI();
		String method = request.getMethod();

		String originalMessage = message;
		message = message.substring(0, message.lastIndexOf("|"));

		if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
			message = "Internal Server Error";
		}

		ApiExceptionDetail apiExceptionDetail = new ApiExceptionDetail(path, status, method, message, validationMessage, new HashMap<>());

		if (validationMessage != null && !validationMessage.isEmpty()) {
			originalMessage += validationMessage.toString();
		}

		writeLog(logLevel, apiExceptionDetail.getErrorCode(), originalMessage);

		return new ResponseEntity<>(apiExceptionDetail, status);
	}

	private void writeLog(String level, String errorCode, String message) {
		String formatMessage = messageFormat(message, errorCode);
		switch (level) {
			case WARN -> log.warn(formatMessage);
			case ERROR -> log.error(formatMessage);
			default -> log.info(formatMessage);
		}
	}

	private String messageFormat(String message, String errorCode) {
		return String.format("ErrorId :: %5s | %s", errorCode, message);
	}
}
