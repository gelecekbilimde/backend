package org.gelecekbilimde.scienceplatform.notification.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.google.firebase.messaging.*;

import org.gelecekbilimde.scienceplatform.config.FCMConfiguration;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;
import org.gelecekbilimde.scienceplatform.notification.repository.NotificationToken;
import org.gelecekbilimde.scienceplatform.notification.repository.UserTokenRepository;
import org.gelecekbilimde.scienceplatform.notification.service.FCMService;
import org.springframework.stereotype.Service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
class FCMServiceImpl implements FCMService {

	private final UserTokenRepository userTokenRepository;
	private final FirebaseMessaging firebaseMessaging = new FCMConfiguration().firebaseMessaging();

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
			.map(NotificationToken::getDeviceToken)
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
