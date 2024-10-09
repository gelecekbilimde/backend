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
			log.error(exception.getMessage(), exception);
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
			log.error(exception.getMessage(), exception);
		}
	}
}
