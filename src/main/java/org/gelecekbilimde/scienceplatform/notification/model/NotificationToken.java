package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.gelecekbilimde.scienceplatform.common.model.BaseDomainModel;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NotificationToken extends BaseDomainModel {

	private Long id;
	private String userId;
	private String deviceId;
	private String deviceToken;

}
