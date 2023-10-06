package org.gelecekbilimde.scienceplatform.notification.client.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class YoutubePlaylistItemsResponse {
	String kind;
	String etag;
	String nextPageToken;
	List<Item> items;
	PageInfo pageInfo;
}
