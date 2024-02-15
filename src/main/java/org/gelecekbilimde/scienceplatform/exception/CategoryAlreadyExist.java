package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CategoryAlreadyExist extends RuntimeException{
	// TODO: RuntimeException can be changed with NotAllowedException
	private String categoryName;
}
