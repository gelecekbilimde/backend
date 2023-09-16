package org.gelecekbilimde.scienceplatform.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationRequest;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationResponse;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
@RestController
@RequestMapping("/notifications")
public class PushNotificationController {


	private final PushNotificationService pushNotificationService;

	public PushNotificationController(PushNotificationService pushNotificationService) {
		this.pushNotificationService = pushNotificationService;
	}

	@PostMapping("/notifications/token")
	public ResponseEntity<PushNotificationResponse> sendPushNotification(@RequestBody PushNotificationRequest request) {
		pushNotificationService.sendPushNotificationToToken(request);
		PushNotificationResponse response = new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent.");

		return ResponseEntity.ok(response);
	}
}
