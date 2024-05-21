package org.gelecekbilimde.scienceplatform.notification.service.impl;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.model.NotificationTokenEntity;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationUserRequest;
import org.gelecekbilimde.scienceplatform.notification.repository.NotificationTokenRepository;
import org.gelecekbilimde.scienceplatform.notification.service.FCMService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
class FCMServiceImpl implements FCMService {

	private final NotificationTokenRepository userTokenRepository;
	private final FirebaseMessaging firebaseMessaging;

	@Override
	public void sendMessageToTokenList(PushNotificationUserRequest request)
		throws FirebaseMessagingException {
		MulticastMessage message = getPreconfiguredMulticastMessageBuilder(request).build();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		BatchResponse response = firebaseMessaging.sendEachForMulticast(message);
		log.info("Sent message to token. {} msg {}", response.getResponses().toString(), jsonOutput);
	}

	@Override
	public void sendMessageToTopic(PushNotificationTopicRequest request)
		throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageBuilder(request).build();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = firebaseMessaging.sendAsync(message).get();
		log.info("Sent message to topic: {}, {} msg {}", request.getTopic(), response, jsonOutput);
	}

	private Message.Builder getPreconfiguredMessageBuilder(PushNotificationTopicRequest request) {
		Notification.Builder notificationBuilder = Notification.builder()
			.setTitle(request.getTitle())
			.setBody(request.getMessage())
			.setImage(request.getThumbnailLink());

		return Message.builder()
			.setTopic(request.getTopic())
			.setNotification(notificationBuilder.build());
	}

	private MulticastMessage.Builder getPreconfiguredMulticastMessageBuilder(PushNotificationUserRequest request) {
		List<String> deviceTokens = userTokenRepository.findAllByUserId(request.getUserId())
			.stream()
			.filter(notificationToken -> notificationToken.getDeviceToken() != null)
			.map(NotificationTokenEntity::getDeviceToken)
			.toList();
		log.info("Find Device Tokens : {}", deviceTokens);

		Notification.Builder notificationBuilder = Notification.builder()
			.setTitle(request.getTitle())
			.setBody(request.getMessage());

		return MulticastMessage.builder()
			.addAllTokens(deviceTokens)
			.setNotification(notificationBuilder.build());
	}
}
