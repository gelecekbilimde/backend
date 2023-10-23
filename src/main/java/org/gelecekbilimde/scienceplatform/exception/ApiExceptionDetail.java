package org.gelecekbilimde.scienceplatform.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ApiExceptionDetail {

	private final String path;
	private final String message;
	private final Integer status;
	private final HttpStatus statusText;
	private final String method;
	private final Map<String, Object> args;
	private final Map<String, String> validationMessage;
	private String errorCode;

	public ApiExceptionDetail(String path, HttpStatus httpStatus, String httpMethod, String message,  @Nullable Map<String,String> validationMessage, @Nullable Map<String, Object> args) {
		this.path = path;
		this.message = message;
		this.statusText = httpStatus;
		this.status = httpStatus.value();
		this.method = httpMethod;
		this.validationMessage = validationMessage != null ? validationMessage : new HashMap<>();
		this.args = args != null ? args : new HashMap<>();

		setErrorCode();
	}

	public String getTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return ZonedDateTime.now(ZoneId.of("Z")).format(formatter);
	}

	private void setErrorCode() {
		this.errorCode = UUID.randomUUID().toString();
	}

}
