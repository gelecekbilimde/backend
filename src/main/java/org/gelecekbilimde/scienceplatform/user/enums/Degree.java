package org.gelecekbilimde.scienceplatform.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Degree {
	PRIMARY_SCHOOL("Primary School"),
	MIDDLE_SCHOOL("Middle School"),
	ASSOCIATE("Associate"),
	BACHELOR("Bachelor"),
	MASTER("Master"),
	DOCTORATE("Doctorate");
	private final String value;
}
