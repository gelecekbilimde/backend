package org.gelecekbilimde.scienceplatform.notification.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationRequest;
@Service
public class PushNotificationService {

	private static final Logger logger = LogManager.getLogger(FCMInitializer.class);

	private final FCMService fcmService;

	@Autowired
	public PushNotificationService(FCMService fcmService) {
		this.fcmService = fcmService;
	}


	public void sendPushNotificationToToken(PushNotificationRequest request) {
		try {
			fcmService.sendMessageToToken(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
