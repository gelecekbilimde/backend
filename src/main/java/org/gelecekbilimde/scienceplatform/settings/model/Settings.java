package org.gelecekbilimde.scienceplatform.settings.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Settings extends BaseDomainModel {

	private Long settingsId;
	private String groupName;
	private String name;
	private String definition;
	private Long userId;
	private boolean hidden;

}
