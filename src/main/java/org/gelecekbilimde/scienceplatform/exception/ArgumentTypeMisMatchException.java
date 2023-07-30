package org.gelecekbilimde.scienceplatform.exception;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class ArgumentTypeMisMatchException extends MethodArgumentTypeMismatchException {


	public ArgumentTypeMisMatchException(Object value, Class<?> requiredType, String name, MethodParameter param, Throwable cause) {
		super(value, requiredType, name, param, cause);
	}
}

