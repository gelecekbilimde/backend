package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationUserRequest {
	private Long userId;
	private String title;
	private String message;

	public PushNotificationUserRequest(Builder builder) {
		this.userId = builder.userId;
		this.title = builder.title;
		this.message = builder.message;
	}

	public static class Builder {
		private Long userId;
		private String title;
		private String message;

		public Builder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public PushNotificationUserRequest build() {
			return new PushNotificationUserRequest(this);
		}
	}
}
