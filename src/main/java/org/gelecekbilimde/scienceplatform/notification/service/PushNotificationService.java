package org.gelecekbilimde.scienceplatform.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;

@Slf4j
@Service
public class PushNotificationService {

	private final FCMService fcmService;

	@Autowired
	public PushNotificationService(FCMService fcmService) {
		this.fcmService = fcmService;
	}

	public void sendPushNotificationToUser(PushNotificationUserRequest request) {
		try {
			fcmService.sendMessageToTokenList(request);
		} catch (Exception exception) {
			log.warn(exception.getMessage(), exception);
		}
	}

	public void sendPushNotificationToTopic(PushNotificationTopicRequest request) {
		try {
			fcmService.sendMessageToTopic(request);
		} catch (Exception exception) {
			log.warn(exception.getMessage(), exception);
		}
	}
}
