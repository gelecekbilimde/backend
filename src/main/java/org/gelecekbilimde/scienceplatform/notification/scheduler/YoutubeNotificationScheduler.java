package org.gelecekbilimde.scienceplatform.notification.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.YoutubeClient;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.YoutubePlaylistItemsResponse;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
class YoutubeNotificationScheduler {
	@Value("${youtubeDataApi.playlistId}")
	private String playlistId;
	@Value("${youtubeDataApi.key}")
	private String apiKey;
	private final YoutubeClient youtubeClient;
	private final PushNotificationService pushNotificationService;
	private String lastVideoId;

	@PostConstruct
	private void init() {
		this.lastVideoId = this.getLastVideo().getId();
	}

	/**
	 * This method will be executed every 8.64 seconds.
	 */
	@Scheduled(fixedRate = 8_640)
	private void sendNotificationForNewVideo() {
		log.info("Checking for new video...");
		YoutubeVideo lastVideo = this.getLastVideo();
		String videoId = lastVideo.getId();

		if (!this.lastVideoId.equals(videoId)) {
			log.info("New video: {}", videoId);
			try {
				pushNotificationService.sendPushNotificationToTopic(
					PushNotificationTopicRequest.builder()
						.topic("youtube-yeni-video")
						.title("Yeni video")
						.message("Yeni video: " + lastVideo.getTitle())
						.build()
				);
			} catch (Exception e) {
				log.error("Error while sending notification.", e);
			}
			this.lastVideoId = videoId;
			log.info("Notifications has been sent.");
			return;
		}
		log.info("No new video. Last video: {}", videoId);
	}

	private YoutubeVideo getLastVideo() {
		YoutubePlaylistItemsResponse playlistItemsResponse;
		try {
			playlistItemsResponse = youtubeClient.getPlaylistItems("snippet",
				playlistId,
				apiKey,
				1);

			return YoutubeVideo.builder()
				.id(playlistItemsResponse.getItems().get(0).getSnippet().getResourceId().getVideoId())
				.title(playlistItemsResponse.getItems().get(0).getSnippet().getTitle())
				.build();
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
			throw new RuntimeException(exception);
		}
	}

	@Builder
	@Getter
	private static class YoutubeVideo {
		private String id;
		private String title;
	}
}
