package org.gelecekbilimde.scienceplatform.exception;

import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ApiExceptionDetail {

	private final String path;
	private final String message;
	private final Integer status;
	private final HttpStatus statusText;
	private final String method;
	private final Map<String, Object> args;

	private String errorCode;

	public ApiExceptionDetail(String path, HttpStatus httpStatus, String httpMethod, String message, @Nullable Map<String, Object> args) {
		this.path = path;
		this.message = message;
		this.statusText = httpStatus;
		this.status = httpStatus.value();
		this.method = httpMethod;
		this.args = args != null ? args : new HashMap<>();

		setErrorCode();
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

	public String getErrorCode() {
		return errorCode;
	}

	private void setErrorCode() {
		this.errorCode = UUID.randomUUID().toString();
	}

	public String getMethod() {
		return method;
	}

	public Map<String, Object> getArgs() {
		return args;
	}
}
