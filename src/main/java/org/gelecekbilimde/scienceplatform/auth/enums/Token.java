package org.gelecekbilimde.scienceplatform.auth.enums;

public enum Token {
	BEARER("Bearer ");

	private String value;

	Token(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

