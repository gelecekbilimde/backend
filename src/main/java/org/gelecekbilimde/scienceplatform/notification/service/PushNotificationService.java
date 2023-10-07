package org.gelecekbilimde.scienceplatform.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.springframework.stereotype.Service;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushNotificationService {

	private final FCMService fcmService;

	/**
	 * Send push notification to user by user id.
	 *
	 * @param request PushNotificationTopicRequest
	 */
	public void sendPushNotificationToUser(PushNotificationUserRequest request) {
		try {
			fcmService.sendMessageToTokenList(request);
		} catch (Exception exception) {
			log.warn(exception.getMessage(), exception);
		}
	}

	/**
	 * Send push notification to topic.
	 *
	 * @param request PushNotificationTopicRequest
	 *
	 * @implNote PushNotificationTopicRequest.builder()
	 * 						.topic("youtube-yeni-video")
	 * 						.title("Yeni video yok")
	 * 						.message("Yeni video yok : " + videoTitle)
	 * 						.build()
	 * 						);
	 *
	 */
	public void sendPushNotificationToTopic(PushNotificationTopicRequest request) {
		try {
			fcmService.sendMessageToTopic(request);
		} catch (Exception exception) {
			log.warn(exception.getMessage(), exception);
		}
	}
}
