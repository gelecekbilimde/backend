package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PushNotificationResponse {
	private Status status;
	private String message;

	public enum Status {
		SUCCESS,
		FAILURE,
		PENDING
	}
}
