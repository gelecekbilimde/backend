package org.gelecekbilimde.scienceplatform.common.model.request;

import lombok.Builder;
import lombok.Getter;
import org.gelecekbilimde.scienceplatform.common.model.enums.MailTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class MailSendRequest {

	private List<String> to;
	private MailTemplate template;
	@Builder.Default
	private Map<String, Object> parameters = new HashMap<>();

}
