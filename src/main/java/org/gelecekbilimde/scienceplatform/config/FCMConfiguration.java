package org.gelecekbilimde.scienceplatform.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Configuration
public class FCMConfiguration {
	@Value("${firebase.service-account.path}")
	private String serviceAccountFilePath;

	@Value("${firebase.service-account.database-url}")
	private String databaseUrl;

	@Bean
	public FirebaseMessaging firebaseMessaging() throws IOException {
		FileInputStream serviceAccount =
			new FileInputStream(serviceAccountFilePath);
		FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl(databaseUrl)
			.build();

		FirebaseApp.initializeApp(options);
		return FirebaseMessaging.getInstance();
	}
}
