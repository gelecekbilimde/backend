package org.gelecekbilimde.scienceplatform.notification.service.impl;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationUserRequest;
import org.gelecekbilimde.scienceplatform.notification.service.FCMService;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
class PushNotificationServiceImpl implements PushNotificationService {

	private final FCMService fcmService;

	/**
	 * Send push notification to user by user id.
	 *
	 * @param request PushNotificationTopicRequest
	 */
	public void sendPushNotificationToUser(PushNotificationUserRequest request) {
		try {
			fcmService.sendMessageToTokenList(request);
		} catch (FirebaseMessagingException exception) {
			log.error("Error while sending push notification to userId: {}", request.getUserId());
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Send push notification to topic.
	 *
	 * @param request PushNotificationTopicRequest
	 * @implNote PushNotificationTopicRequest.builder()
	 * .topic("youtube-yeni-video")
	 * .title("Yeni video yok")
	 * .message("Yeni video yok : " + videoTitle)
	 * .build()
	 * );
	 */
	public void sendPushNotificationToTopic(PushNotificationTopicRequest request) {
		try {
			fcmService.sendMessageToTopic(request);
		} catch (InterruptedException | ExecutionException exception) {
			log.error("Error while sending push notification to topic: {}", request.getTopic());
			Thread.currentThread().interrupt();
		}
	}

}
