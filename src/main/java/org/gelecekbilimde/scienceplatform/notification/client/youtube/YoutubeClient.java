package org.gelecekbilimde.scienceplatform.notification.client.youtube;


import org.gelecekbilimde.scienceplatform.notification.client.youtube.model.YoutubePlaylistItemsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "youtubeClient",
	url = "${youtubeDataApi.url}"
)
public interface YoutubeClient {
	@RequestMapping(method = RequestMethod.GET,
		value = "/playlistItems",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	YoutubePlaylistItemsResponse getPlaylistItems(@RequestParam("part") String part,
												  @RequestParam("playlistId") String playlistId,
												  @RequestParam("key") String key,
												  @RequestParam("maxResults") Integer maxResults);
}
