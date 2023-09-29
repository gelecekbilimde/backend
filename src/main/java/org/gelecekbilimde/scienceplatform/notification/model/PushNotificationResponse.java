package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationResponse {
	private Status status;
	private String message;

	public enum Status {
		SUCCESS,
		FAILURE,
		PENDING
	}

	public PushNotificationResponse(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
	}

	public static class Builder {
		private Status status;
		private String message;

		public Builder status(Status status) {
			this.status = status;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public PushNotificationResponse build() {
			return new PushNotificationResponse(this);
		}
	}
}
