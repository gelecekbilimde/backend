package org.gelecekbilimde.scienceplatform.notification.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PushNotificationTopicRequest {
	private String title;
	private String message;
	private String topic;
	private String thumbnailLink;
}
