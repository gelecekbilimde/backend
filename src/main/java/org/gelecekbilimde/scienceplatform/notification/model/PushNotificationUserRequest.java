package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PushNotificationUserRequest {
	private Long userId;
	private String title;
	private String message;
}
