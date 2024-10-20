package org.gelecekbilimde.scienceplatform.notification.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.YoutubeClient;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.response.YoutubePlaylistItemsResponse;
import org.gelecekbilimde.scienceplatform.notification.exception.LastYouTubeVideoNotFoundException;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
class YoutubeNotificationScheduler {

	private final YoutubeClient youtubeClient;
	private final PushNotificationService pushNotificationService;


	@Value("${youtubeDataApi.playlistId}")
	private String playlistId;

	@Value("${youtubeDataApi.key}")
	private String apiKey;

	private String lastVideoId;

	@PostConstruct
	private void init() {
		this.lastVideoId = this.getLastVideo().getId();
	}


	/**
	 * This method will be executed every minute.
	 */
	@Scheduled(fixedRate = 60_000)
	private void sendNotificationForNewVideo() {
		log.debug("Checking for new video...");
		YoutubeVideo lastVideo = this.getLastVideo();
		String videoId = lastVideo.getId();

		if (!this.lastVideoId.equals(videoId)) {
			log.info("New video: {}", videoId);

			PushNotificationTopicRequest notificationTopicRequest = PushNotificationTopicRequest.builder()
				.topic("youtube-yeni-video")
				.title("Yeni Video Yayınlandı!")
				.message("Yeni video: " + lastVideo.getTitle())
				.thumbnailLink(lastVideo.getThumbnailLink())
				.build();

			pushNotificationService.sendPushNotificationToTopic(notificationTopicRequest);
			log.info("Notifications has been sent to topic: {}", notificationTopicRequest.getTopic());

			this.lastVideoId = videoId;
		}
	}

	private YoutubeVideo getLastVideo() {
		try {
			YoutubePlaylistItemsResponse playlistItemsResponse =
				youtubeClient.getPlaylistItems("snippet", playlistId, apiKey, 1);

			return YoutubeVideo.builder()
				.id(playlistItemsResponse.getItems().get(0).getSnippet().getResourceId().getVideoId())
				.title(playlistItemsResponse.getItems().get(0).getSnippet().getTitle())
				.thumbnailLink(playlistItemsResponse.getItems().get(0).getSnippet().getThumbnails().getMaxres().getUrl())
				.build();
		} catch (Exception exception) {
			throw new LastYouTubeVideoNotFoundException(exception);
		}
	}

	@Builder
	@Getter
	private static class YoutubeVideo {
		private String id;
		private String title;
		private String thumbnailLink;
	}

}
