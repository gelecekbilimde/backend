package org.gelecekbilimde.scienceplatform.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@ControllerAdvice
public class Handler {

	private static final Logger LOGGER 	= LoggerFactory.getLogger(Handler.class);
	private static final String ERROR 	= "error";
	private static final String WARN 	= "warn";
	private static final String INFO 	= "info";

	@ExceptionHandler(value = {ClientException.class})
	public ResponseEntity<Object> handleClientException(ClientException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return trowException(request, status, message,INFO, new HashMap<>());
	}

	@ExceptionHandler(value = {NotAllowedException.class})
	public ResponseEntity<Object> handleNotAllowedException(NotAllowedException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		String message = e.getMessage();

		return trowException(request, status, message,WARN, new HashMap<>());
	}

	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = e.getMessage();

		return trowException(request, status, message,INFO, new HashMap<>());
	}

	@ExceptionHandler(value = {UnAuthorizedException.class})
	public ResponseEntity<Object> handleUnAuthorizedException(UnAuthorizedException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		return trowException(request, status, message,WARN, new HashMap<>());
	}

	@ExceptionHandler(value = {ServerException.class})
	public ResponseEntity<Object> handleServerException(ServerException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return trowException(request, status, message, ERROR, new HashMap<>());
	}
	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		return trowException(request, status, message,ERROR, new HashMap<>());
	}

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = "Doğrulama Hatası";
		HashMap<String,String> validationMessage = new HashMap<>();

		for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
			validationMessage.put(error.getObjectName(),error.getDefaultMessage());
		}

		// Tüm alan düzeyinde hataları alıyoruz (Örneğin: @Valid kullanılan alan düzeyinde hatalar)
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			validationMessage.put(error.getField(),error.getDefaultMessage());
		}

		return trowException(request, status, message,ERROR, validationMessage);
	}


	@ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleArgumentTypeMisMatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		String paramName = e.getName();
		String expectedType = e.getRequiredType().getSimpleName();
		String invalidValue = e.getValue().toString();

		Object[] its = e.getRequiredType().getEnumConstants();

		StringJoiner enumList = new StringJoiner(", ");
		for (Object it : its){
			enumList.add(it.toString());
		}

		// İstisna mesajını hazırla
		String message = "Hatalı parametre: " + paramName + ". " +
				"Beklenen tip: " + expectedType + ". " +
				"Geçersiz değer: " + invalidValue + ". " +
				"Parametre, değerlerden biri olmalıdır: "+enumList.toString() ;


		return trowException(request, status, message,ERROR, new HashMap<>());
	}


	private ResponseEntity<Object> trowException(HttpServletRequest request, HttpStatus status, String message, String logLevel, @Nullable Map<String, String> validationMessage) {

		String path = request.getRequestURI();
		String method = request.getMethod();

		String originalMessage = message;
		if (status == HttpStatus.INTERNAL_SERVER_ERROR){
			message = "Beklenmeyen bir hata oldu Hemen ilgileneceğiz";
		}

		Exception exception = new Exception(path, status, method, message, validationMessage, new HashMap<>());

		if (!validationMessage.isEmpty()){
			originalMessage += validationMessage.toString();
		}

		writeLog(logLevel, exception.errorCode, originalMessage);

		return new ResponseEntity<>(exception, status);
	}

	private void writeLog(String level, String errorCode, String message)
	{
		String formatMessage = messageFormat(message,errorCode);
		switch (level) {
			case "warn" -> LOGGER.warn(formatMessage);
			case "error" -> LOGGER.error(formatMessage);
			default -> LOGGER.info(formatMessage);
		}
	}

	private String messageFormat(String message, String errorCode)
	{
		return  String.format("ErrorId :: %5s | %s", errorCode, message);
	}
}
