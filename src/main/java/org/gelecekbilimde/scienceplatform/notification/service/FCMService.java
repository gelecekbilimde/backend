package org.gelecekbilimde.scienceplatform.notification.service;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.firebase.messaging.*;

import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;
import org.gelecekbilimde.scienceplatform.notification.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@RequiredArgsConstructor
public class FCMService {

	private final UserTokenRepository userTokenRepository;
	private final Logger logger = LogManager.getLogger(FCMService.class);
	private List<String> deviceTokens;

	// TODO: sendMessageToTokenList
	public void sendMessageToTokenList(PushNotificationUserRequest request)
		throws FirebaseMessagingException {
		MulticastMessage message = getPreconfiguredMulticastMessage(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		BatchResponse response = sendAndGetMultiResponse(message);
		logger.info("Sent message to token. Device token: " + deviceTokens + ", " + response.getResponses().toString() + " msg "+jsonOutput);
	}

	public List<String> getTokensFromRepository() {
//		deviceTokens = userTokenRepository.findAllDeviceTokens();
		return userTokenRepository.findAllDeviceTokens();
	}

	//TODO : delete tokens that are not registered from database

	public void deleteTokensFromRepository(List<String> tokens) {
		userTokenRepository.deleteDevicesById(tokens);
	}

	//device_id - user_id - token - created_at

/*
	public void sendMessageToToken(PushNotificationRequest request)
		throws InterruptedException, ExecutionException {
		MulticastMessage message = getPreconfiguredMulticastMessage(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
	}*/

	public void sendMessageToTopic(PushNotificationTopicRequest request)
		throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToTopic(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetSingleResponse(message);
		logger.info("Sent message to topic: " + request.getTopic() + ", " + response + " msg " + jsonOutput);
	}

	private BatchResponse sendAndGetMultiResponse(MulticastMessage message) throws FirebaseMessagingException {
		return FirebaseMessaging.getInstance().sendEachForMulticast(message);
	}

	private String  sendAndGetSingleResponse(Message message) throws ExecutionException, InterruptedException {
		return FirebaseMessaging.getInstance().sendAsync(message).get();
	}


	private AndroidConfig getAndroidConfig(String topic) {
		return AndroidConfig.builder()
			.setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
			.setPriority(AndroidConfig.Priority.HIGH)
			.setNotification(AndroidNotification.builder()
				.setTag(topic).build()).build();
	}
	private ApnsConfig getApnsConfig(String topic) {
		return ApnsConfig.builder()
			.setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
	}

	private Message getPreconfiguredMessageToTopic(PushNotificationTopicRequest request) {
		return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
			.build();
	}

	private Message.Builder getPreconfiguredMessageBuilder(PushNotificationTopicRequest request) {
		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());

		// Create a Notification.Builder and set title and message
		Notification.Builder notificationBuilder = Notification.builder()
			.setTitle(request.getTitle())
			.setBody(request.getMessage());

		return Message.builder()
			.setApnsConfig(apnsConfig)
			.setAndroidConfig(androidConfig)
			.setNotification(notificationBuilder.build()); // Build the Notification here
	}

	/*
	private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
			.build();
	}

	private Message getPreconfiguredMessageToTopic(PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
			.build();
	}
	private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
			.build();
	}
	private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
		return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
			.build();
	}

	private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());

		// Create a Notification.Builder and set title and message
		Notification.Builder notificationBuilder = Notification.builder()
			.setTitle(request.getTitle())
			.setBody(request.getMessage());

		return Message.builder()
			.setApnsConfig(apnsConfig)
			.setAndroidConfig(androidConfig)
			.setNotification(notificationBuilder.build()); // Build the Notification here
	}*/

	//TODO: multicast message send message to multiple devices
	private MulticastMessage getPreconfiguredMulticastMessage(PushNotificationUserRequest request) {
		return getPreconfiguredMulticastMessageBuilder(request).build();
	}


	private MulticastMessage.Builder getPreconfiguredMulticastMessageBuilder(PushNotificationUserRequest request) {
//		//use .addAllTokens(request.getToken()) for multiple devices
//		AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
//		ApnsConfig apnsConfig = getApnsConfig(request.getTopic());

		deviceTokens = getTokensFromRepository();

		// Create a Notification.Builder and set title and message
		Notification.Builder notificationBuilder = Notification.builder()
			.setTitle(request.getTitle())
			.setBody(request.getMessage());

		return MulticastMessage.builder()
//			.setApnsConfig(apnsConfig)
//			.setAndroidConfig(androidConfig)
			.addAllTokens(deviceTokens)
			.setNotification(notificationBuilder.build()); // Build the Notification here
	}
}
