package org.gelecekbilimde.scienceplatform.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenClaims {

	ALGORITHM("alg"),
	TYPE("typ"),
	USER_ID("userId"),
	USER_FIRST_NAME("userFirstName"),
	USER_LAST_NAME("userLastName"),
	USER_MAIL("userMail"),
	USER_STATUS("userStatus"),
	USER_ROLE("userRole"),
	USER_PERMISSIONS("userPermissions");

	private final String value;

}
