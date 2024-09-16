package org.gelecekbilimde.scienceplatform.common.model.response;

import lombok.Getter;
import org.gelecekbilimde.scienceplatform.common.util.RandomUtil;
import org.springframework.http.HttpStatus;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {

	private final String path;
	private final String message;
	private final Integer status;
	private final HttpStatus statusText;
	private final String method;
	private final Map<String, Object> args;
	private final Map<String, String> validationMessage;
	private String errorCode;

	public ErrorResponse(String path, HttpStatus httpStatus, String httpMethod, String message, @Nullable Map<String, String> validationMessage, @Nullable Map<String, Object> args) {
		this.path = path;
		this.message = message;
		this.statusText = httpStatus;
		this.status = httpStatus.value();
		this.method = httpMethod;
		this.validationMessage = validationMessage != null ? validationMessage : new HashMap<>();
		this.args = args != null ? args : new HashMap<>();

		setErrorCode();
	}

	private void setErrorCode() {
		this.errorCode = RandomUtil.generateUUID();
	}

}
