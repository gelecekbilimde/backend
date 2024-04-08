package org.gelecekbilimde.scienceplatform.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentNotFoundException extends NotFoundException {
	private Long parentId;

	public ParentNotFoundException(Long parentId) {
		super("Parent not found with id: " + parentId);
		this.parentId = parentId;
	}
}
