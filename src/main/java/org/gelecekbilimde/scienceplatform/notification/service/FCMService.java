package org.gelecekbilimde.scienceplatform.notification.service;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.firebase.messaging.*;

import org.springframework.stereotype.Service;

import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class FCMService {
	private final Logger logger = LogManager.getLogger(FCMService.class);

	public void sendMessageToToken(PushNotificationRequest request)
		throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToToken(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
	}

	public void sendMessageToTopic(PushNotificationRequest request)
		throws InterruptedException, ExecutionException {
		Message message = getPreconfiguredMessageToTopic(request);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(message);
		String response = sendAndGetResponse(message);
		logger.info("Sent message to topic: " + request.getTopic() + ", " + response + " msg " + jsonOutput);
	}

	private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
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
	}
}
