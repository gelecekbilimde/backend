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

	private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

	@ExceptionHandler(value = {ClientException.class})
	public ResponseEntity<Object> handleClientException(ClientException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = e.getMessage();

		LOGGER.info(message);
		return trowException(request, status, message);
	}

	@ExceptionHandler(value = {NotAllowedException.class})
	public ResponseEntity<Object> handleNotAllowedException(NotAllowedException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		String message = e.getMessage();

		LOGGER.info(message);
		return trowException(request, status, message);
	}

	@ExceptionHandler(value = {NotFoundException.class})
	public ResponseEntity<Object> handleNotFoundException(NotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		String message = e.getMessage();

		LOGGER.info(message);
		return trowException(request, status, message);
	}

	@ExceptionHandler(value = {UnAuthorizedException.class})
	public ResponseEntity<Object> handleUnAuthorizedException(UnAuthorizedException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNAUTHORIZED;
		String message = e.getMessage();

		LOGGER.info(message);
		return trowException(request, status, message);
	}

	@ExceptionHandler(value = {ServerException.class})
	public ResponseEntity<Object> handleServerException(ServerException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = e.getMessage();

		LOGGER.error(message);
		return trowException(request, status, message);
	}

	private ResponseEntity<Object> trowException(HttpServletRequest request, HttpStatus status, String message) {

		String path = request.getRequestURI();
		String method = request.getMethod();

		Exception exception = new Exception(path, status, method, message, new HashMap<>());

		return new ResponseEntity<>(exception, status);
	}
}
