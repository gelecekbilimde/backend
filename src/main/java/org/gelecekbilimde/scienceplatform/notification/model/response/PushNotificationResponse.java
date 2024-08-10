package org.gelecekbilimde.scienceplatform.notification.model.response;

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
