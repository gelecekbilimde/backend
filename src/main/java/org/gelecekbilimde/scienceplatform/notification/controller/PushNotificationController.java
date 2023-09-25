package org.gelecekbilimde.scienceplatform.notification.controller;

/*import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationResponse;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;*/

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class PushNotificationController {

//	private final PushNotificationService pushNotificationService;
	/*
	These methods are not for use in this project. They are just for testing purposes.

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
	}*/
}
