package org.gelecekbilimde.scienceplatform.exception;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CategoryHasChild extends RuntimeException{
	// TODO: RuntimeException can be changed with NotAllowedException
	private Long categoryId;
}
