package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PushNotificationUserRequest {
	private String title;
	private String message;
	private List<String> token;
	private String userId;
}
