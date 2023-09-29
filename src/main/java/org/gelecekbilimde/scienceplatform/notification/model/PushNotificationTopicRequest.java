package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushNotificationTopicRequest {
	private String title;
	private String message;
	private String topic;

	public PushNotificationTopicRequest(Builder builder) {
		this.title = builder.title;
		this.message = builder.message;
		this.topic = builder.topic;
	}

	public static class Builder {
		private String title;
		private String message;
		private String topic;

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder topic(String topic) {
			this.topic = topic;
			return this;
		}

		public PushNotificationTopicRequest build() {
			return new PushNotificationTopicRequest(this);
		}
	}
}
