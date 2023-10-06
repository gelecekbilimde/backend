package org.gelecekbilimde.scienceplatform.notification.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.YoutubeClient;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.YoutubePlaylistItemsResponse;
import org.gelecekbilimde.scienceplatform.notification.model.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
class YoutubeNotificationScheduler {
	private final YoutubeClient youtubeClient;
	private final PushNotificationService pushNotificationService;
	private String lastVideoId;

	@PostConstruct
	private void init() {
		this.lastVideoId = this.getLastVideo().getId();
	}

	/**
	 * This method will be executed every 86.4 seconds.
	 */
	@Scheduled(fixedRate = 86_400)
	private void sendNotificationForNewVideo() {
		log.info("Checking for new video...");
		YoutubeVideo lastVideo = this.getLastVideo();
		String videoId = lastVideo.getId();

		if (!this.lastVideoId.equals(videoId)) {
			//TODO: send notification

			log.info("New video: {}", videoId);
			pushNotificationService.sendPushNotificationToTopic(
				PushNotificationTopicRequest.builder()
					.topic("youtube-yeni-video")
					.title("Yeni video")
					.message("Yeni video: " + videoId + " " + lastVideo.getTitle())
					.build()
			);

			this.lastVideoId = videoId;
			log.info("Notifications has been sent.");
			return;
		}
		log.info("No new video. Last video: {}", videoId);
	}

	private YoutubeVideo getLastVideo() {
		YoutubePlaylistItemsResponse playlistItemsResponse = youtubeClient.getPlaylistItems("snippet",
			"UU03cpKIZShIWoSBhfVE5bog",
			"AIzaSyDKNZRqoxFE5_rqRpjKvWEUrhoXfawu3jo",
			1);
		return YoutubeVideo.builder()
			.id(playlistItemsResponse.getItems().get(0).getSnippet().getResourceId().getVideoId())
			.title(playlistItemsResponse.getItems().get(0).getSnippet().getTitle())
			.build();
	}

	@Builder
	@Getter
	private static class YoutubeVideo {
		private String id;
		private String title;
	}
}
