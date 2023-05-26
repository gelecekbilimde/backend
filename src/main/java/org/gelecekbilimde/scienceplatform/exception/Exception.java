package org.gelecekbilimde.scienceplatform.exception;

import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Exception {

	private final String path;
	private final String message;
	private final Integer status;
	private final HttpStatus statusText;
	private final String method;

	private final Map<String, Object> args;

	public Exception(String path, HttpStatus httpStatus, String httpMethod, String message, @Nullable Map<String, Object> args) {
		this.path = path;
		this.message = message;
		this.statusText = httpStatus;
		this.status = httpStatus.value();
		this.method = httpMethod;
		this.args = args != null ? args : new HashMap<>();

		String errorId = getErrorId();
		String timestamp = getTimestamp();

		//	Map<String, Object> request = getRequest(args);
	}

	public String getPath() {
		return path;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatusText() {
		return statusText;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return ZonedDateTime.now(ZoneId.of("Z")).format(formatter);
	}

	public String getErrorId() {
		return UUID.randomUUID().toString();
	}

	public String getMethod() {
		return method;
	}

	public Map<String, Object> getArgs() {
		return args;
	}

	/*
	public Map<String, Object> getRequest(Map<String, Object> args) {
		args = args == null ? new HashMap<>() : args;
		Map<String, Object> request = new HashMap<>();
		request.put("method", this.httpMethod);
		request.put("path", this.path);
		request.put("args", args);

		return request;
	}


	 */
}
