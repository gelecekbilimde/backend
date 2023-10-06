package org.gelecekbilimde.scienceplatform.notification.client.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
