package org.gelecekbilimde.scienceplatform.notification.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Configuration
public class FCMInitializer {
    private static final Logger logger = LogManager.getLogger(FCMInitializer.class);
	@PostConstruct
	public void initialize() {
		try {
			FileInputStream serviceAccount =  new FileInputStream("src/main/resources/serviceAccountKey.json");

			FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			logger.error("Error initializing Firebase:", e);
		}
	}
}
