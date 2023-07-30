package org.gelecekbilimde.scienceplatform.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Executable;

public class ValidationException extends MethodArgumentNotValidException {

	public ValidationException(MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
	}

	public ValidationException(Executable executable, BindingResult bindingResult) {
		super(executable, bindingResult);
	}
}

