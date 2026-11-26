package org.gelecekbilimde.scienceplatform.notification.service;

import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationUserRequest;

public interface PushNotificationService {

	void sendPushNotificationToUser(PushNotificationUserRequest request);

	void sendPushNotificationToTopic(PushNotificationTopicRequest request);

}
