package org.gelecekbilimde.scienceplatform.notification.client.youtube.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class YoutubePlaylistItemsResponse {
	private List<Item> items;
	private PageInfo pageInfo;

	@Getter
	@Setter
	public static class Item {
		private Snippet snippet;

		@Getter
		@Setter
		public static class Snippet {
			private String publishedAt;
			private String title;
			private String channelTitle;
			private String playlistId;
			private ResourceId resourceId;
			@Getter
			@Setter
			public static class ResourceId {
				private String videoId;
			}
		}
	}

	@Getter
	@Setter
	public static class PageInfo {
		private Integer totalResults;
		private Integer resultsPerPage;
	}
}
