package org.gelecekbilimde.scienceplatform.settings.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Settings {

	private Long settingsId;
	private String groupName;
	private String name;
	private String definition;
	private Long userId;
	private boolean hidden;

}
