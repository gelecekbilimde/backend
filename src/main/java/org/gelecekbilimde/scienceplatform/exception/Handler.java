package org.gelecekbilimde.scienceplatform.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class Handler {

	private static final Logger LOGGER 	= LoggerFactory.getLogger(Handler.class);
	private static final String ERROR 	= "error";
	private static final String WARN 	= "warn";
	private static final String INFO 	= "info";

	@ExceptionHandler(value = {ClientException.class})
	public ResponseEntity<Object> handleClientException(ClientException e, HttpServletRequest request) {

		// IS_ALREADT_REGISTER
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return trowException(request, status, message,INFO);
	}

	@ExceptionHandler(value = {NotAllowedException.class})
	public ResponseEntity<Object> handleNotAllowedException(NotAllowedException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		String message = e.getMessage();

		return trowException(request, status, message,WARN);
	}

	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = e.getMessage();

		return trowException(request, status, message,INFO);
	}

	@ExceptionHandler(value = {UnAuthorizedException.class})
	public ResponseEntity<Object> handleUnAuthorizedException(UnAuthorizedException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		return trowException(request, status, message,WARN);
	}

	@ExceptionHandler(value = {ServerException.class})
	public ResponseEntity<Object> handleServerException(ServerException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = e.getMessage();

		return trowException(request, status, message, ERROR);
	}
	@ExceptionHandler(value = {UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		return trowException(request, status, message,ERROR);
	}

	private ResponseEntity<Object> trowException(HttpServletRequest request, HttpStatus status, String message,String logLevel) {

		String path = request.getRequestURI();
		String method = request.getMethod();

		String originalMessage = message;
		if (status == HttpStatus.INTERNAL_SERVER_ERROR){
			message = "Beklenmeyen bir hata oldu Hemen ilgileneceğiz";
		}

		Exception exception = new Exception(path, status, method, message, new HashMap<>());

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
