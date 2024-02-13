package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class CategoryNotFoundException extends RuntimeException{
	private Long categoryId;
}
