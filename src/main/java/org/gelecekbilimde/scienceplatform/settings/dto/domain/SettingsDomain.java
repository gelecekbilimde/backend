package org.gelecekbilimde.scienceplatform.settings.dto.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.exception.ClientException;


@Data
@EqualsAndHashCode()
@SuperBuilder
public class SettingsDomain {


	private Long settingsId;
	private String groupName;
	private String name;
	private String definition;
	private Long userId;
	private boolean hidden;
}
