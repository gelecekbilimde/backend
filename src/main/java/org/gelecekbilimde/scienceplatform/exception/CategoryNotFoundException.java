package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class CategoryNotFoundException extends RuntimeException {
	// TODO: RuntimeException can be replaced with NotFoundException
	private Long categoryId;
}
