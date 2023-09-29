package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PushNotificationResponse {
	private Status status;
	private String message;

	public enum Status {
		SUCCESS,
		FAILURE,
		PENDING
	}
}
