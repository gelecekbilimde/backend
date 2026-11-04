package org.gelecekbilimde.scienceplatform.notification.scheduler;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.YoutubeClient;
import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.response.YoutubePlaylistItemsResponse;
import org.gelecekbilimde.scienceplatform.notification.exception.LastYouTubeVideoNotFoundException;
import org.gelecekbilimde.scienceplatform.notification.model.request.PushNotificationTopicRequest;
import org.gelecekbilimde.scienceplatform.notification.service.PushNotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

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

	private boolean disabledWarningLogged = false;


	/**
	 * Her dakika YouTube oynatma listesindeki son videoyu kontrol eder.
	 * <p>
	 * İlk çalışmada yalnızca mevcut son videoyu referans olarak kaydeder, bildirim göndermez.
	 * API anahtarı tanımlı değilse veya YouTube'a ulaşılamazsa uygulamayı düşürmez, sadece loglar.
	 */
	@Scheduled(fixedRate = 60_000)
	void sendNotificationForNewVideo() {

		if (StringUtils.isBlank(apiKey) || StringUtils.isBlank(playlistId)) {
			if (!disabledWarningLogged) {
				log.warn("YOUTUBE_DATA_API_KEY or YOUTUBE_DATA_API_PLAYLIST_ID is not set, YouTube notifications are disabled");
				disabledWarningLogged = true;
			}
			return;
		}

		log.debug("Checking for new video...");
		final YoutubeVideo lastVideo;
		try {
			lastVideo = this.getLastVideo();
		} catch (Exception exception) {
			log.warn("Last YouTube video could not be fetched: {}", exception.getMessage());
			return;
		}

		String videoId = lastVideo.getId();
		if (this.lastVideoId == null) {
			this.lastVideoId = videoId;
			return;
		}

		if (this.lastVideoId.equals(videoId)) {
			return;
		}

		log.info("New video: {}", videoId);

		PushNotificationTopicRequest notificationTopicRequest = PushNotificationTopicRequest.builder()
			.topic("youtube-yeni-video")
			.title("Yeni Video Yayınlandı!")
			.message("Yeni video: " + lastVideo.getTitle())
			.thumbnailLink(lastVideo.getThumbnailLink())
			.build();

		try {
			pushNotificationService.sendPushNotificationToTopic(notificationTopicRequest);
			log.info("Notifications has been sent to topic: {}", notificationTopicRequest.getTopic());
		} catch (Exception exception) {
			log.error("Push notification could not be sent for video {}: {}", videoId, exception.getMessage());
		}

		this.lastVideoId = videoId;
	}

	private YoutubeVideo getLastVideo() {
		try {
			YoutubePlaylistItemsResponse playlistItemsResponse =
				youtubeClient.getPlaylistItems("snippet", playlistId, apiKey, 1);

			YoutubePlaylistItemsResponse.Item.Snippet snippet = playlistItemsResponse.getItems().get(0).getSnippet();
			return YoutubeVideo.builder()
				.id(snippet.getResourceId().getVideoId())
				.title(snippet.getTitle())
				.thumbnailLink(findBestThumbnailLink(snippet.getThumbnails()))
				.build();
		} catch (Exception exception) {
			throw new LastYouTubeVideoNotFoundException(exception);
		}
	}

	/**
	 * Her videonun "maxres" küçük resmi olmayabilir; en büyükten küçüğe doğru ilk mevcut olanı döner.
	 */
	private static String findBestThumbnailLink(YoutubePlaylistItemsResponse.Item.Thumbnails thumbnails) {
		if (thumbnails == null) {
			return null;
		}
		return Stream.of(thumbnails.getMaxres(), thumbnails.getStandard(), thumbnails.getHigh(),
				thumbnails.getMedium(), thumbnails.getDefaultThumbnail())
			.filter(Objects::nonNull)
			.map(YoutubePlaylistItemsResponse.Item.Thumbnails.Thumbnail::getUrl)
			.filter(StringUtils::isNotBlank)
			.findFirst()
			.orElse(null);
	}

	@Builder
	@Getter
	private static class YoutubeVideo {
		private String id;
		private String title;
		private String thumbnailLink;
	}

}
