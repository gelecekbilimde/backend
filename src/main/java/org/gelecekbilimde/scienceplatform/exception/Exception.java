package org.gelecekbilimde.scienceplatform.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
public class Exception {

	private final String path;
	private final String message;
	private final Integer status;
	private final HttpStatus statusText;
	private final String method;

	private final Map<String, Object> args;
	private final Map<String, String> validationMessage;

	public  String errorCode;

	public Exception(String path, HttpStatus httpStatus, String httpMethod, String message, @Nullable Map<String,String> validationMessage, @Nullable Map<String, Object> args) {
		this.path 			= path;
		this.message 		= message;
		this.statusText 	= httpStatus;
		this.status 		= httpStatus.value();
		this.method 		= httpMethod;
		this.validationMessage = validationMessage 	!= null ? validationMessage : new HashMap<>();
        this.args = args 	!= null ? args : new HashMap<>();


		setErrorCode();
		this.errorCode 		= getErrorCode();
		String timestamp 	= getTimestamp();

		//	Map<String, Object> request = getRequest(args);
	}

	public String getTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return ZonedDateTime.now(ZoneId.of("Z")).format(formatter);
	}

	public void setErrorCode() {
		this.errorCode =  UUID.randomUUID().toString();
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
