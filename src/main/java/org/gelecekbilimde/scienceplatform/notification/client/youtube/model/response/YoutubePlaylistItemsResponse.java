package org.gelecekbilimde.scienceplatform.notification.client.youtube.model.response;

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
			private Thumbnails thumbnails;
			private String channelTitle;
			private String playlistId;
			private ResourceId resourceId;
			@Getter
			@Setter
			public static class ResourceId {
				private String videoId;
			}
		}
		@Getter
		@Setter
		public static class Thumbnails {
			private Thumbnail defaultThumbnail;
			private Thumbnail medium;
			private Thumbnail high;
			private Thumbnail standard;
			private Thumbnail maxres;

			@Getter
			@Setter
			public static class Thumbnail {
				private String url;
				private Integer width;
				private Integer height;
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
