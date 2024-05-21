package org.gelecekbilimde.scienceplatform.notification.client.youtube;

import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.response.YoutubePlaylistItemsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
	value = "youtubeClient",
	url = "${youtubeDataApi.url}"
)
public interface YoutubeClient {

	@GetMapping("/playlistItems")
	YoutubePlaylistItemsResponse getPlaylistItems(@RequestParam("part") String part,
												  @RequestParam("playlistId") String playlistId,
												  @RequestParam("key") String key,
												  @RequestParam("maxResults") Integer maxResults);

}
