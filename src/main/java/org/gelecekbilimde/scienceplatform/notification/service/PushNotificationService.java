package org.gelecekbilimde.scienceplatform.notification.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;

@Service
public class PushNotificationService {

	private static final Logger logger = LogManager.getLogger(FCMInitializer.class);

	private final FCMService fcmService;

	@Autowired
	public PushNotificationService(FCMService fcmService) {
		this.fcmService = fcmService;
	}


	public void sendPushNotificationToUser(PushNotificationUserRequest request) {
		try {
			fcmService.sendMessageToTokenList(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


	public void sendPushNotificationToTopic(PushNotificationTopicRequest request) {
		try {
			fcmService.sendMessageToTopic(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
