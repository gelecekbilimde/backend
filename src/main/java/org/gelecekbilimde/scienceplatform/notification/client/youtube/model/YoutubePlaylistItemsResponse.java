package org.gelecekbilimde.scienceplatform.notification.client.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class YoutubePlaylistItemsResponse {
	String kind;
	String etag;
	String nextPageToken;
	Item items;
	PageInfo pageInfo;

	@Getter
	@AllArgsConstructor
	public class Item {
		String kind;
		String etag;
		String id;
		Snippet snippet;

		@Getter
		@AllArgsConstructor
		public class Snippet {
			String publishedAt;
			String channelId;
			String title;
			String description;
			Thumbnails thumbnails;
			String channelTitle;
			String playlistId;
			int position;
			ResourceId resourceId;
			String videoOwnerChannelTitle;
			String videoOwnerChannelId;

			@Getter
			@AllArgsConstructor
			public class Thumbnails {
				Thumbnail defaultThumbnail;
				Thumbnail mediumThumbnail;
				Thumbnail highThumbnail;
				Thumbnail standardThumbnail;
				Thumbnail maxresThumbnail;

				@Getter
				@AllArgsConstructor
				public class Thumbnail {
					String url;
					int width;
					int height;
				}
			}

			@Getter
			@AllArgsConstructor
			public class ResourceId {
				String kind;
				String videoId;
			}
		}
	}

	@Getter
	@AllArgsConstructor
	public class PageInfo {
		int totalResults;
		int resultsPerPage;
	}
}
