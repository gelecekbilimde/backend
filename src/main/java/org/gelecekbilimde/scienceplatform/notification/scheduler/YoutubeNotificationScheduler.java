package org.gelecekbilimde.scienceplatform.notification.scheduler;

import lombok.RequiredArgsConstructor;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.YoutubeClient;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.YoutubePlaylistItemsResponse;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@EnableScheduling
@RequiredArgsConstructor
class YoutubeNotificationScheduler {
	private final YoutubeClient youtubeClient;
	private String lastVideoId;

	@PostConstruct
	private void init() {
		this.lastVideoId = this.getLastVideo().getItems().get(0).getSnippet().getResourceId().getVideoId();
	}

	/**
	 * This method will be executed every 86.4 seconds.
	 */
	@Scheduled(fixedRate = 86_400)
	private void sendNotificationForNewVideo() {
		YoutubePlaylistItemsResponse response = this.getLastVideo();
		String videoId = response.getItems().get(0).getSnippet().getResourceId().getVideoId();

		if (!this.lastVideoId.equals(videoId)) {
			//TODO: send notification
			this.lastVideoId = videoId;
			System.out.println("New video: " + videoId);
			return;
		}
		System.out.println("No new video");
	}

	private YoutubePlaylistItemsResponse getLastVideo() {
		return youtubeClient.getPlaylistItems("snippet",
			"UU03cpKIZShIWoSBhfVE5bog",
			"AIzaSyDKNZRqoxFE5_rqRpjKvWEUrhoXfawu3jo",
			1);
	}
}
