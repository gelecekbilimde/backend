package org.gelecekbilimde.scienceplatform.notification.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PushNotificationUserRequest {

	private String userId;
	private String title;
	private String message;

}
