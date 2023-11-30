package org.gelecekbilimde.scienceplatform.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenClaims {
	JWT_ID("jti"),
	TYPE("typ"),
	USER_ID("userId"),
	FULL_NAME("fullName"),
	MAIL("mail"),
	ROLE_ID("roleId"),
	USER_STATUS("userStatus"),
	ROLE_NAME("roleName"),
	SCOPE("scope"),

	SUBJECT("sub"),


	ISSUED_AT("iat"),
	EXPIRES_AT("exp"),
	ALGORITHM("alg"),


	TYPE_VAL("JWT"),
	ISSUER("gelecekbilimde.net"),
	GUEST_FULL_NAME("Ziyaretçi"),



	;
	private final String value;
}
