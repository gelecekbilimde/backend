package org.gelecekbilimde.scienceplatform.settings.dto.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


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
