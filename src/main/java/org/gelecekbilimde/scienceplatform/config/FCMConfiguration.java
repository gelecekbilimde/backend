package org.gelecekbilimde.scienceplatform.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Configuration
public class FCMConfiguration {

//	@PostConstruct
//	public void initialize() throws IOException {
//			FileInputStream serviceAccount =
//				new FileInputStream(ResourceUtils.getFile("classpath:serviceAccountKey.json"));
////				new FileInputStream("serviceAccountKey.json");
//
//			FirebaseOptions options = FirebaseOptions.builder()
//				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//				.setDatabaseUrl("https://push-notification-projec-86e67-default-rtdb.firebaseio.com")
//				.build();
//
//			FirebaseApp.initializeApp(options);
//	}

	@Bean
	public FirebaseMessaging firebaseMessaging() throws IOException {
		FileInputStream serviceAccount =
			new FileInputStream(ResourceUtils.getFile("classpath:serviceAccountKey.json"));
//				new FileInputStream("serviceAccountKey.json");

		FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://push-notification-projec-86e67-default-rtdb.firebaseio.com")
			.build();

		FirebaseApp.initializeApp(options);
		return FirebaseMessaging.getInstance();
	}
}
