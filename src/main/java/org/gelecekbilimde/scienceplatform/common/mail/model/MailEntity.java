package org.gelecekbilimde.scienceplatform.common.mail.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Properties;

@Getter
@Builder
public class MailEntity {
	private String senderName;

	private String senderEmail;

	private String senderPassword;

	private Properties properties;

	@Setter
	private String title;

	@Setter
	private String template;

	@Setter
	private String to;

	@Setter
	private Map<String, String> values;
}
