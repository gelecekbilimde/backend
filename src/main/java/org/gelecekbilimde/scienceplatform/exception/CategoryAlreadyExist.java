package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CategoryAlreadyExist extends RuntimeException{
	private String categoryName;
}
