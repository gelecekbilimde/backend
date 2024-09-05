package org.gelecekbilimde.scienceplatform.common.mail.model.request;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class EmailSendRequest {
	private String to;
	private String templateFileName;
	@Builder.Default
	private Map<String, String> templateVariables = new HashMap<>();
}
