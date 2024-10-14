package org.gelecekbilimde.scienceplatform.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailTemplate {

	USER_VERIFICATION("user-verification.html"),
	USER_WELCOME("user-welcome.html");

	private final String file;

}
