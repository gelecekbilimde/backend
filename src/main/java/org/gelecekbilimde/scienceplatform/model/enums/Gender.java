package org.gelecekbilimde.scienceplatform.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum Gender {

	GENDER_MALE("male"),
	GENDER_FEMALE("female");

	@Getter
	private final String gender;

}
