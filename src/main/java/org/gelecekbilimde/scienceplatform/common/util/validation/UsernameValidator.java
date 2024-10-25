package org.gelecekbilimde.scienceplatform.common.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

class UsernameValidator implements ConstraintValidator<Username, String> {

	private static final String USERNAME_REGEX = "^[a-zA-Z0-9]{3,20}$";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!StringUtils.hasText(value)) {
			return true;
		}

		String lowerCasedValue = value.toLowerCase();

		if (value.startsWith(" ") || value.endsWith(" ")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("name must not start or end with whitespace")
				.addConstraintViolation();
			return false;
		}

		if (!lowerCasedValue.matches(USERNAME_REGEX)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Username must be 3-20 characters long and alphanumeric")
				.addConstraintViolation();
			return false;
		}

		return true;
	}
}
