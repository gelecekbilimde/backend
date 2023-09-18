package org.gelecekbilimde.scienceplatform.notification.controller;

import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationResponse;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
@RestController
@RequestMapping("/notifications")
public class PushNotificationController {


	private final PushNotificationService pushNotificationService;

	public PushNotificationController(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}

	//repository oluştur repodan user id ile token listesi çek sonra token listesine göre notification gönder


	@PostMapping("/user")
	public ResponseEntity<PushNotificationResponse> sendPushNotification(@RequestBody PushNotificationUserRequest request) {
		pushNotificationService.sendPushNotificationToUser(request);
		PushNotificationResponse response = new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent.");

		return ResponseEntity.ok(response);
	}

	@PostMapping("/topic")
	public ResponseEntity<PushNotificationResponse> sendPushNotificationToTopic(@RequestBody PushNotificationTopicRequest request) {
		pushNotificationService.sendPushNotificationToTopic(request);
		PushNotificationResponse response = new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent.");

		return ResponseEntity.ok(response);
	}
}
