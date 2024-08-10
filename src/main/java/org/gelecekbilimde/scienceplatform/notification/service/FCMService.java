package org.gelecekbilimde.scienceplatform.notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationUserRequest;

import java.util.concurrent.ExecutionException;

public interface FCMService {

	void sendMessageToTokenList(PushNotificationUserRequest request) throws FirebaseMessagingException;

	void sendMessageToTopic(PushNotificationTopicRequest request) throws InterruptedException, ExecutionException;

}
