package org.gelecekbilimde.scienceplatform.common.model;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class MailSendRequest {
	private String to;
	private String templateFileName;
	@Builder.Default
	private Map<String, String> templateVariables = new HashMap<>();
}
